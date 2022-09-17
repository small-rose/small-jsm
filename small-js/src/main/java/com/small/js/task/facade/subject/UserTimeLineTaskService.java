package com.small.js.task.facade.subject;

import com.small.common.utils.StringUtils;
import com.small.js.task.facade.base.BaseTaskService;
import com.small.js.web.domain.SubjectDataInfo;
import com.small.js.web.domain.TimeLineData;
import com.small.js.web.service.ITimeLineDataService;
import kong.unirest.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/8/23 20:39
 * @version: v1.0
 */
@Slf4j
@Service
public class UserTimeLineTaskService extends BaseTaskService {

    @Autowired
    private ITimeLineDataService timeLineDataService;

    public void start(String subjectId, String userSlug, int start, int end) throws ParseException {
        setSubjectId(subjectId);
        start(userSlug, start, end);
    }

    @Override
    protected void start(String userSlug, int start, int end) throws UnirestException, ParseException {

        loadHeaderCookies();

        String url = gettTimeLineDefaultUrl(userSlug);
        Document page = getPage(url);
        //feed-852751078
        String feedId = page.select(".note-list >li").get(0).attr("id");
        String maxId = feedId.substring(5);

        List<SubjectDataInfo> listTotal = new ArrayList<>(200);
        Document document ;
        log.info("LP动态地址：" +gettTimeLineUrl(userSlug, maxId)+1);
        for (int i = end; i >= start; i--) {
            String tmpUrl = gettTimeLineUrl(userSlug, maxId)+i ;
            document = getPage(tmpUrl);
            List<SubjectDataInfo> list2 = parseData(document, subjectId);
            listTotal.addAll(list2);

        }
        log.info("========================== TASK GAME OVER ======================================");
        log.info("本次任务更新时间 {} 篇文章！",listTotal.size());

    }

    @Override
    protected List<SubjectDataInfo> parseData(Document document, String subjectId) throws UnirestException, ParseException {
        List<SubjectDataInfo> list = new ArrayList<>(20);
        Elements cList = document.select("#list-container > ul > li");

        System.out.println(" clist " + cList.size());
        for (Element c :cList){
            //2022-08-23T12:10:02+08:00
            String id = c.attr("id").substring(5);
            String likeTime = c.select("div.content div.author div.info span").attr("data-datetime");
            String showDate = likeTime.substring(0, 10);
            String formatLikeTime = likeTime.substring(0, 19).replace("T"," ");
            Date likeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formatLikeTime);
            String title = c.select(".title").text();
            String wenAddr = c.select(".title").attr("abs:href");
            String nickname = c.select("div.origin-author a").get(0).text();
            String userMain = c.select("div.origin-author a").attr("abs:href");
            String readNum = c.select("div.content div.meta a").get(1).text();
            String commentNum = c.select("div.content div.meta a").get(2).text();
            String likeNum = c.select("div.content div.meta span").get(0).text();
            Elements commentEle = c.select("div.content p.comment");
            String comment = "";
            if (commentEle!=null){
                 comment = commentEle.get(0).text();
            }


            TimeLineData timeLine = new TimeLineData();
            timeLine.setTitle(title);
            timeLine.setWenId(id);//feed-num
            timeLine.setWenUrl(wenAddr);
            timeLine.setWenSlug(wenAddr.substring(wenAddr.lastIndexOf("/")+1, wenAddr.length()-1));

            timeLine.setUserSlug(userMain.substring(userMain.lastIndexOf("/")+1, userMain.length()-1));
            timeLine.setNickName(nickname);
            timeLine.setUserSlug(subjectId);
            timeLine.setShouDate(showDate);
            timeLine.setLikeTime(likeDate);
            if (StringUtils.isNotBlank(comment)){
                timeLine.setLpComment(comment);
            }
            timeLine.setReadNum(Long.parseLong(readNum));
            timeLine.setCommentNum(Long.parseLong(commentNum));
            timeLine.setLikeNum(Long.parseLong(likeNum));

            System.out.println("=========================================");
            System.out.println("showDate  = " + showDate);
            System.out.println("likeTime  = " + formatLikeTime);
            System.out.println("title = " + title);
            System.out.println("title = " + title);
            System.out.println("nickname = " + nickname);
            System.out.println("readNum = " + readNum);
            System.out.println("commentNum = " + commentNum);
            System.out.println("likeNum = " + likeNum);
            System.out.println("=========================================");

            timeLineDataService.saveData(timeLine);

        }
        return  list;
    }



}
