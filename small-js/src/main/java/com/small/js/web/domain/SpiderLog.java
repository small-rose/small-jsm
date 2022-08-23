package com.small.js.web.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.small.common.annotation.Excel;
import com.small.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 上榜抓取日志对象 js_spider_log
 * 
 * @author small-rose
 * @date 2022-08-19
 */
public class SpiderLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 执行时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "执行时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date execTime;

    /** 排名日期 */
    @Excel(name = "排名日期")
    private String rankDate;

    /** 排名类型 */
    @Excel(name = "排名类型")
    private String rankType;

    /** 结果 */
    @Excel(name = "结果")
    private String result;

    /** 执行状态 */
    @Excel(name = "执行状态")
    private String status;

    /** 执行结果 */
    @Excel(name = "执行结果")
    private String updateResult;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setExecTime(Date execTime) 
    {
        this.execTime = execTime;
    }

    public Date getExecTime() 
    {
        return execTime;
    }
    public void setRankDate(String rankDate) 
    {
        this.rankDate = rankDate;
    }

    public String getRankDate() 
    {
        return rankDate;
    }
    public void setRankType(String rankType) 
    {
        this.rankType = rankType;
    }

    public String getRankType() 
    {
        return rankType;
    }
    public void setResult(String result) 
    {
        this.result = result;
    }

    public String getResult() 
    {
        return result;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setUpdateResult(String updateResult) 
    {
        this.updateResult = updateResult;
    }

    public String getUpdateResult() 
    {
        return updateResult;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("execTime", getExecTime())
            .append("rankDate", getRankDate())
            .append("rankType", getRankType())
            .append("result", getResult())
            .append("status", getStatus())
            .append("updateResult", getUpdateResult())
            .toString();
    }
}
