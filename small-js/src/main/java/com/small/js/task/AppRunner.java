package com.small.js.task;

import com.small.js.web.domain.SubjectInfo;
import com.small.js.web.service.ISubjectInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/8/21 15:21
 * @version: v1.0
 */

@Slf4j
@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    private ISubjectInfoService subjectInfoService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<SubjectInfo> list = new ArrayList<>(10);
//
        list.add(new SubjectInfo(1L,"0527ee052b5b","我与简书的故事 ","woyujianshudegushi"));
        list.add(new SubjectInfo(2L,"f61832508891","理事会点赞汇总","lishihuidianzanhuizong"));
        list.add(new SubjectInfo(3L,"57cc936b828b","简书文友汇推文","wenyouhuituiwen"));
//
        list.stream().forEach(zt-> {
            if (subjectInfoService.selectSubjectInfoBySubjectId(zt.getSubjectSlug())==null){
                subjectInfoService.insertSubjectInfo(zt);
            }
        });
        log.info("简书专题数据初始化成功！");
    }
}
