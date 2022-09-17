package com.small.js.task.vo;

import lombok.Data;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/9/17 11:50
 * @version: v1.0
 */
@Data
public class MailBean {

    /**
     * 接收人
     */
    private String recipient ;

    /**
     * 邮件主题
     */
    private String subject ;
    /**
     * 邮件内容
     */
    private String content ;

    /**
     * 邮件附件路径
     */
    private String filePath ;


    /**
     * 邮件附件路径
     */
    private String fileName ;
}
