package com.small.js.web.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.small.common.annotation.Excel;
import com.small.common.core.domain.BaseEntity;

/**
 * 专题信息对象 js_subject_info
 * 
 * @author small-rose
 * @date 2022-08-19
 */
@AllArgsConstructor
@NoArgsConstructor
public class SubjectInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 专题slug */
    @Excel(name = "专题slug")
    private String subjectSlug;

    /** 专题名称 */
    @Excel(name = "专题名称")
    private String title;

    /** 专题名称拼音 */
    @Excel(name = "专题名称拼音")
    private String titlePy;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSubjectSlug(String subjectSlug) 
    {
        this.subjectSlug = subjectSlug;
    }

    public String getSubjectSlug() 
    {
        return subjectSlug;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setTitlePy(String titlePy) 
    {
        this.titlePy = titlePy;
    }

    public String getTitlePy() 
    {
        return titlePy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("subjectSlug", getSubjectSlug())
            .append("title", getTitle())
            .append("titlePy", getTitlePy())
            .toString();
    }
}
