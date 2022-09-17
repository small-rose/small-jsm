package com.small.js.task;

import com.small.js.task.facade.rank.ArticleRankTaskService;
import com.small.js.task.facade.rank.UpdateNickNameTaskService;
import com.small.js.task.facade.subject.SubjectDataInfoTaskService;
import com.small.js.web.domain.SubjectInfo;
import com.small.js.web.service.ISubjectInfoService;
import kong.unirest.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Project : small
 * @Author : zhangzongyuan
 * @Description : [ AppTask ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/8/19 10:18
 * @Version ： 1.0
 **/
@Slf4j
@Component("appTask")
public class AppTask {
    @Autowired
    private ArticleRankTaskService articleRankTaskService ;
    @Autowired
    private UpdateNickNameTaskService updateNickNameTaskService ;
    @Autowired
    private SubjectDataInfoTaskService subjectDataInfoTaskService;

    @Autowired
    private ISubjectInfoService subjectInfoService ;

    //@Scheduled(cron = "0 0/5 0-1 * * ? ")
    public void rank(){

        log.info("定时抓取文章排名任务开始执行！！");
        try {
            articleRankTaskService.catchRankData(null);

        } catch (UnirestException e) {
            e.printStackTrace();
        }
        log.info("定时抓取文章排名任务执行完成！！");
    }

    //@Scheduled(cron = "0 0 0/1 * * ? ")
    //@Scheduled(cron = "0 0/25 * * * ? ")
    public void updateName(){

        log.info("定时更新昵称任务开始执行！！");
        try {
            updateNickNameTaskService.updateNickName(null);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        log.info("定时更新昵称任务执行完成！！");
    }



    public void subjectTask(String subjectId, Integer start, Integer end){


        log.info("本次执行专题ID : " + subjectId);
        SubjectInfo subjectInfo = subjectInfoService.selectSubjectInfoBySubjectId(subjectId);
        if (subjectInfo==null){
            throw new RuntimeException("找不到要执行的专题数据");
        }
        log.info("简书专题[ {} ]任务开始执行！！", subjectInfo.getTitle());
        try {
            //每天爬取100条记录
            //subjectDataInfoTaskService.start("f61832508891",1,10);
            subjectDataInfoTaskService.start(subjectId, start, end);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        log.info("简书专题[ {} ]任务执行完成！！", subjectInfo.getTitle());
    }




}
