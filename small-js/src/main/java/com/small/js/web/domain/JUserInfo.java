package com.small.js.web.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.small.common.annotation.Excel;
import com.small.common.core.domain.BaseEntity;

/**
 * 简书用户对象 js_user_info
 * 
 * @author small-rose
 * @date 2022-08-19
 */
public class JUserInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String nickName;

    /** 用户昵称拼音 */
    @Excel(name = "用户昵称拼音")
    private String nickNamePy;

    /** 是否是推荐人 */
    @Excel(name = "是否是推荐人")
    private Long precommender;

    /** 用户slug */
    @Excel(name = "用户slug")
    private String slug;

    /** 主页地址 */
    @Excel(name = "主页地址")
    private String slugUrl;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setNickNamePy(String nickNamePy) 
    {
        this.nickNamePy = nickNamePy;
    }

    public String getNickNamePy() 
    {
        return nickNamePy;
    }
    public void setPrecommender(Long precommender) 
    {
        this.precommender = precommender;
    }

    public Long getPrecommender() 
    {
        return precommender;
    }
    public void setSlug(String slug) 
    {
        this.slug = slug;
    }

    public String getSlug() 
    {
        return slug;
    }
    public void setSlugUrl(String slugUrl) 
    {
        this.slugUrl = slugUrl;
    }

    public String getSlugUrl() 
    {
        return slugUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("nickName", getNickName())
            .append("nickNamePy", getNickNamePy())
            .append("precommender", getPrecommender())
            .append("slug", getSlug())
            .append("slugUrl", getSlugUrl())
            .toString();
    }
}
