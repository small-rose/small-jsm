package com.small.js.web.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.small.common.annotation.Excel;
import com.small.common.core.domain.BaseEntity;

/**
 * 上榜数据对象 js_article_rank
 * 
 * @author small-rose
 * @date 2022-08-19
 */
public class ArticleRank extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 头像地址 */
    @Excel(name = "头像地址")
    private String authorAvatar;

    /** 作者昵称 */
    @Excel(name = "作者昵称")
    private String authorNickname;

    /** 上榜日期 */
    @Excel(name = "上榜日期")
    private String rankDate;

    /** 上榜排名 */
    @Excel(name = "上榜排名")
    private Long rankNo;

    /** 文章标题 */
    @Excel(name = "文章标题")
    private String title;

    /** 作者收益 */
    @Excel(name = "作者收益")
    private String authorFp;

    /** 作者昵称拼音 */
    @Excel(name = "作者昵称拼音")
    private String authorNicknamePy;

    /** 总收益 */
    @Excel(name = "总收益")
    private String fp;

    /** 用户slug */
    @Excel(name = "用户slug")
    private String slug;

    /** 点赞收益 */
    @Excel(name = "点赞收益")
    private String voterFp;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAuthorAvatar(String authorAvatar) 
    {
        this.authorAvatar = authorAvatar;
    }

    public String getAuthorAvatar() 
    {
        return authorAvatar;
    }
    public void setAuthorNickname(String authorNickname) 
    {
        this.authorNickname = authorNickname;
    }

    public String getAuthorNickname() 
    {
        return authorNickname;
    }
    public void setRankDate(String rankDate) 
    {
        this.rankDate = rankDate;
    }

    public String getRankDate() 
    {
        return rankDate;
    }
    public void setRankNo(Long rankNo) 
    {
        this.rankNo = rankNo;
    }

    public Long getRankNo() 
    {
        return rankNo;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setAuthorFp(String authorFp) 
    {
        this.authorFp = authorFp;
    }

    public String getAuthorFp() 
    {
        return authorFp;
    }
    public void setAuthorNicknamePy(String authorNicknamePy) 
    {
        this.authorNicknamePy = authorNicknamePy;
    }

    public String getAuthorNicknamePy() 
    {
        return authorNicknamePy;
    }
    public void setFp(String fp) 
    {
        this.fp = fp;
    }

    public String getFp() 
    {
        return fp;
    }
    public void setSlug(String slug) 
    {
        this.slug = slug;
    }

    public String getSlug() 
    {
        return slug;
    }
    public void setVoterFp(String voterFp) 
    {
        this.voterFp = voterFp;
    }

    public String getVoterFp() 
    {
        return voterFp;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("authorAvatar", getAuthorAvatar())
            .append("authorNickname", getAuthorNickname())
            .append("rankDate", getRankDate())
            .append("rankNo", getRankNo())
            .append("title", getTitle())
            .append("authorFp", getAuthorFp())
            .append("authorNicknamePy", getAuthorNicknamePy())
            .append("fp", getFp())
            .append("slug", getSlug())
            .append("voterFp", getVoterFp())
            .toString();
    }
}
