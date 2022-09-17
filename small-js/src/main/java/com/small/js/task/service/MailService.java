package com.small.js.task.service;

import com.alibaba.fastjson.JSON;
import com.small.js.task.vo.MailBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/9/17 11:45
 * @version: v1.0
 */
@Slf4j
@Component
public class MailService {

    @Autowired
    JavaMailSender javaMailSender ;

    private static String  MAIL_SENDER = "small-rose@qq.com" ;
    /**
     * 带附件的邮件
     * @param mailBean
     */
    public void sendAttachmentMail(MailBean mailBean) {
        MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(MAIL_SENDER);
            mimeMessageHelper.setTo(mailBean.getRecipient());
            mimeMessageHelper.setSubject(mailBean.getSubject());
            mimeMessageHelper.setText(mailBean.getContent());
            //文件路径
            FileSystemResource file = new FileSystemResource(new File(mailBean.getFilePath()));
            mimeMessageHelper.addAttachment(mailBean.getFileName(), file);
            log.info("send ...  {}" , JSON.toJSONString(mailBean));
            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("邮件发送失败", e);
        }
    }

}
