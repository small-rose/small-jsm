package com.small.js.task.facade.rank;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.small.js.config.IDService;
import com.small.js.constans.JSConstants;
import com.small.js.task.service.ArticleRankService;
import com.small.js.task.service.SpiderLogService;
import com.small.js.util.PinYinSmallUtil;
import com.small.js.util.SamllDateUtil;
import com.small.js.web.domain.ArticleRank;
import com.small.js.web.domain.SpiderLog;
import kong.unirest.GetRequest;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/7/30 23:37
 * @version: v1.0
 */
@Slf4j
@Service
public class ArticleRankTaskService {

    @Autowired
    private SpiderLogService spiderLogService;
    @Autowired
    private ArticleRankService articleRankService ;
    @Autowired
    private IDService idService ;

    public String catchRankData(String start, String end) throws UnirestException {

        String yyyyMMdd = start ;
        if (StringUtils.hasText(end)){
            LocalDate yyyyMMdd1 = LocalDate.parse(start, DateTimeFormatter.BASIC_ISO_DATE);
            LocalDate yyyyMMdd2 = LocalDate.parse(end, DateTimeFormatter.BASIC_ISO_DATE);
            List<LocalDate> localDates = SamllDateUtil.getsAllDatesInTheDateRange(yyyyMMdd1, yyyyMMdd2);
            log.info("抓取日期区间开始 " + yyyyMMdd1.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            log.info("抓取日期区间结束 " + yyyyMMdd2.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            for (LocalDate billDate : localDates){
                catchRankData(billDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            }
            log.info("日期区间{} 到 {} 的数据抓取完成！", yyyyMMdd1, yyyyMMdd2);
        }else{
            yyyyMMdd = start ;
            List<SpiderLog> list = spiderLogService.selectByRankDateRankType(yyyyMMdd, "AR");

            if (!CollectionUtils.isEmpty(list)){
                SpiderLog spiderLog = list.get(0);
                if ("S".equals(spiderLog.getStatus())) {
                    log.info("日期 " + yyyyMMdd + " 的排名已经抓取");
                    return "ALREADY";
                }else {
                    log.info("日期 " + yyyyMMdd + " 的排名上次抓取失败");
                }
            }
            String result = catchAndSaveRankData(yyyyMMdd);

            spiderLogService.saveLog(yyyyMMdd, result,"AR");
            //list.stream().forEach(System.out::println);
        }

        return  "SUCCESS";
    }


    public String catchRankData(String date) throws UnirestException {
        String yyyyMMdd = date;
        if(!StringUtils.hasText(yyyyMMdd)){
            LocalDate localDate = LocalDate.now().minusDays(1);
            yyyyMMdd = localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        }

        Long dateCount = articleRankService.countByDate(yyyyMMdd);
        log.info(" 本次抓取  yyyyMMdd = " +yyyyMMdd +"数据");
        if (dateCount> 0 && dateCount > 100){
            Integer delCount = articleRankService.deleteByDate(yyyyMMdd);
            log.info("本次删除无效数据：{} " +delCount, yyyyMMdd);
            SpiderLog spiderLog = new SpiderLog();
            spiderLog.setRankType("AR");
            spiderLog.setRankDate(yyyyMMdd);
            spiderLogService.deleteSpiderLog(spiderLog);
            log.info("本次删除日志数据：{} " , yyyyMMdd);
        }

        log.info("本次抓取的上榜日期为" + yyyyMMdd);
        List<SpiderLog> list = spiderLogService.selectByRankDateRankType(yyyyMMdd, "AR");

        if (!CollectionUtils.isEmpty(list)){
            SpiderLog spiderLog = list.get(0);
            if ("S".equals(spiderLog.getStatus())) {
                log.info("日期 " + yyyyMMdd + " 的排名已经抓取");
                return "ALREADY";
            }else {
                log.info("日期 " + yyyyMMdd + " 的排名抓取失败");
            }
        }
        String result = catchAndSaveRankData(yyyyMMdd);

        spiderLogService.saveLog(yyyyMMdd, result,"AR");
        //list.stream().forEach(System.out::println);
        return  "SUCCESS";
    }


    public String catchAndSaveRankData(String yyyyMMdd) throws UnirestException {
        log.info("开始抓取 " + yyyyMMdd +" 的文章排名数据！");
        GetRequest getRequest = Unirest.get(JSConstants.RANK_URL_PRE +yyyyMMdd);
        HttpResponse<String> stringResult = getRequest.asString();
        int status = stringResult.getStatus();
        if (status!=200 ){
            log.info("---抓取排名接口异常");
            return "Fail|"+status ;
        }
        String body = stringResult.getBody();
        JSONObject responseJson = JSONObject.parseObject(body);
        String respDate = (String) responseJson.get("date");
        if (!yyyyMMdd.equals(respDate)){
            log.info("---请求日期与响应日期不符！！");
            return "Fail";
        }
        JSONArray notes = (JSONArray) responseJson.get("notes");
        List<ArticleRank> list = JSONObject.parseArray(notes.toString(),  ArticleRank.class);
        long size = list.size() ;
        if (size ==0){
            return "Fail|没有数据";
        }
        ArticleRank articleRank = null ;
        for(int index = 0 ; index < size ; index ++) {
            articleRank = list.get(index);
            articleRank.setId(idService.getNextId());
            articleRank.setRankNo((long)(index+1));
            articleRank.setRankDate(yyyyMMdd);
            if (articleRank.getTitle()==null){
                articleRank.setTitle("该文章不可访问");
            }
            if (articleRank.getAuthorNickname()==null){
                articleRank.setAuthorNickname("该用户不在简书");
            }

            articleRank.setAuthorNicknamePy(PinYinSmallUtil.getPinYin(articleRank.getAuthorNickname()));
            //System.out.println(articleRank.toString());
        }

        articleRankService.saveAll(list);
        log.info("排名数据保存成功！");
        return "SUCCESS";
    }





}
