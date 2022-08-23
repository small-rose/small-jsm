package com.small.js.web.vo;

import lombok.Data;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/8/20 18:26
 * @version: v1.0
 */
@Data
public class RecommenderVO {

    private Long id;

    private String recommender;

    /** 推荐人slug */
    private String recommenderSlug;

    private String shouDate;
}
