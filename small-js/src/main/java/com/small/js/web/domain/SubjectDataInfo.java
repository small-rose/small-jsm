package com.small.js.web.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.small.common.annotation.Excel;
import com.small.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 专题数据对象 js_subject_data_info
 * 
 * @author small-rose
 * @date 2022-08-19
 */
public class SubjectDataInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 评论数据 */
    private String comments;

    /** 理事会赞赏 */
    private Long lpReward;

    /** 作者昵称 */
    @Excel(name = "作者昵称", sort = 2)
    private String nickName;

    /** 推荐人 */
    @Excel(name = "推荐人", sort = 3)
    private String recommender;

    /** 推荐人slug */
    private String recommenderSlug;

    /** 赞赏数据 */
    private String rewards;

    /** 收录日期 */
    @Excel(name = "收录日期", sort = 1, dateFormat = "mm.dd")
    private String shouDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date shouTime;
    /** 专题slug */
    //@Excel(name = "专题slug")
    private String subjectId;

    /** 文章标题 */
    //@Excel(name = "文章标题")
    private String title;

    /** 用户slug */
    //@Excel(name = "用户slug")
    private String userSlug;

    /** 文章id */
    private String wenId;

    /** 文章slug */
    private String wenSlug;

    /** 文章地址 */
    //@Excel(name = "文章地址")
    private String wenUrl;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setComments(String comments) 
    {
        this.comments = comments;
    }

    public String getComments() 
    {
        return comments;
    }
    public void setLpReward(Long lpReward) 
    {
        this.lpReward = lpReward;
    }

    public Long getLpReward() 
    {
        return lpReward;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setRecommender(String recommender) 
    {
        this.recommender = recommender;
    }

    public String getRecommender() 
    {
        return recommender;
    }
    public void setRecommenderSlug(String recommenderSlug) 
    {
        this.recommenderSlug = recommenderSlug;
    }

    public String getRecommenderSlug() 
    {
        return recommenderSlug;
    }
    public void setRewards(String rewards) 
    {
        this.rewards = rewards;
    }

    public String getRewards() 
    {
        return rewards;
    }
    public void setShouDate(String shouDate) 
    {
        this.shouDate = shouDate;
    }

    public String getShouDate() 
    {
        return shouDate;
    }
    public void setSubjectId(String subjectId) 
    {
        this.subjectId = subjectId;
    }

    public String getSubjectId() 
    {
        return subjectId;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setUserSlug(String userSlug) 
    {
        this.userSlug = userSlug;
    }

    public String getUserSlug() 
    {
        return userSlug;
    }
    public void setWenId(String wenId) 
    {
        this.wenId = wenId;
    }

    public String getWenId() 
    {
        return wenId;
    }
    public void setWenSlug(String wenSlug) 
    {
        this.wenSlug = wenSlug;
    }

    public String getWenSlug() 
    {
        return wenSlug;
    }
    public void setWenUrl(String wenUrl) 
    {
        this.wenUrl = wenUrl;
    }

    public String getWenUrl() 
    {
        return wenUrl;
    }

    public Date getShouTime() {
        return shouTime;
    }

    public void setShouTime(Date shouTime) {
        this.shouTime = shouTime;
    }



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("comments", getComments())
            .append("lpReward", getLpReward())
            .append("nickName", getNickName())
            .append("recommender", getRecommender())
            .append("recommenderSlug", getRecommenderSlug())
            .append("rewards", getRewards())
                .append("shouDate", getShouDate())
                .append("shouTime", getShouTime())
            .append("updateTime", getUpdateTime())
            .append("subjectId", getSubjectId())
            .append("title", getTitle())
            .append("userSlug", getUserSlug())
            .append("wenId", getWenId())
            .append("wenSlug", getWenSlug())
            .append("wenUrl", getWenUrl())
            .toString();
    }
}
