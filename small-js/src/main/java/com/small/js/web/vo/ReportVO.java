package com.small.js.web.vo;

import lombok.Data;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/8/19 22:23
 * @version: v1.0
 */
@Data
public class ReportVO {

    private String shouDate ;
    private String recommender ;

    private int count ;

    public  String getShouDateFormat() {
        return shouDate.replaceAll("-","");
    }
}
