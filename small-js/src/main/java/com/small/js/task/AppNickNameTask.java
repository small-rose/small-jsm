package com.small.js.task;

import com.small.js.task.facade.subject.LPNickNameTaskService;
import com.small.js.task.facade.subject.UserTimeLineTaskService;
import com.small.js.web.domain.SubjectInfo;
import com.small.js.web.service.ISubjectInfoService;
import kong.unirest.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/8/27 15:35
 * @version: v1.0
 */

@Slf4j
@Component("appNN")
public class AppNickNameTask {

    @Autowired
    private LPNickNameTaskService lpNickNameTaskService ;

    @Autowired
    private UserTimeLineTaskService userTimeLineTaskService;

    @Autowired
    private ISubjectInfoService subjectInfoService ;


    public String lpNickNameTask(){
        log.info("理事会成员昵称检查同步任务开始执行！！");
        try {

            lpNickNameTaskService.start(null, 0, 0);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        log.info("理事会成员昵称检查同步任务开始结束！！");
        return "SUCCESS";
    }



    public String timeLineTask(String subjectId, String userSlug,Integer start, Integer end){

        log.info("本次更新专题ID : " + subjectId);
        SubjectInfo subjectInfo = subjectInfoService.selectSubjectInfoBySubjectId(subjectId);
        if (subjectInfo==null){
            throw new RuntimeException("找不到要执行的专题数据");
        }
        log.info("简书专题[ {} ]任务开始执行！！", subjectInfo.getTitle());
        try {

            userTimeLineTaskService.start(subjectId, userSlug,  start, end);
        } catch (UnirestException | ParseException e) {
            e.printStackTrace();
        }
        log.info("简书专题[ {} ]任务执行完成！！", subjectInfo.getTitle());
        return "SUCCESS";
    }

}
