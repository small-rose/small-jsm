package com.small.js.task;

import com.small.common.config.SmallConfig;
import com.small.common.core.domain.AjaxResult;
import com.small.common.utils.poi.ExcelUtil;
import com.small.js.task.service.MailService;
import com.small.js.task.vo.MailBean;
import com.small.js.web.domain.SubjectDataInfo;
import com.small.js.web.service.ISubjectDataInfoService;
import kong.unirest.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/9/17 11:57
 * @version: v1.0
 */
@Slf4j
@Component("appMail")
public class AppMailTask {

    @Autowired
    private ISubjectDataInfoService subjectDataInfoService;
    @Autowired
    private MailService mailService;

    public String sendMail(String today){
        log.info("发送邮件任务开始执行！！");
        LocalDate localDate = null;
        if (StringUtils.hasText(today)){
             localDate = LocalDate.parse(today, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        try {

            Map params = new HashMap<>();
            if (StringUtils.hasText(today)){
                params.put( "beginShouDate",localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }else{
                params.put( "beginShouDate",LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
            SubjectDataInfo subjectDataInfo = new SubjectDataInfo();
            subjectDataInfo.setParams(params);
            List<SubjectDataInfo> list = subjectDataInfoService.selectSubjectDataInfoList(subjectDataInfo, "asc");
            if (CollectionUtils.isEmpty(list)){
                log.info("没有可以发送的数据");
                return "没有可以发送的数据";
            }
            String title = list.get(0).getShouDate()+"专题收录共"+list.size() +"篇" ;
            log.info("title : {}" ,title);
            ExcelUtil<SubjectDataInfo> util = new ExcelUtil<>(SubjectDataInfo.class);
            //return util.exportExcel(list, "专题收录数据", list.get(0).getShouDate()+"专题收录", list.get(0).getShouDate()+"专题收录");
            AjaxResult result = util.exportExcel(list, "专题收录数据", list.get(0).getShouDate()+"专题收录");
            String fileName = (String) result.get(AjaxResult.MSG_TAG);
            String filePath = SmallConfig.getDownloadPath() + fileName;
            MailBean mailBean = new MailBean();
            mailBean.setFileName(fileName);
            mailBean.setFilePath(filePath);
            mailBean.setSubject(title);
            mailBean.setContent(title.concat("，详见附件。"));
            log.info("sendMailTo : " + SmallConfig.getMailTo());
            mailBean.setRecipient(SmallConfig.getMailTo());
            mailService.sendAttachmentMail(mailBean);
            log.info("发送成功！");
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        log.info("发送邮件任务执行结束！！");
        return "SUCCESS";
    }
}
