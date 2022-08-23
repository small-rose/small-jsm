package com.small.js.web.service;

import com.small.js.web.domain.SpiderLog;

import java.util.List;


/**
 * 上榜抓取日志Service接口
 * 
 * @author small-rose
 * @date 2022-08-19
 */
public interface ISpiderLogService 
{
    /**
     * 查询上榜抓取日志
     * 
     * @param id 上榜抓取日志主键
     * @return 上榜抓取日志
     */
    public SpiderLog selectSpiderLogById(Long id);

    /**
     * 查询上榜抓取日志列表
     * 
     * @param spiderLog 上榜抓取日志
     * @return 上榜抓取日志集合
     */
    public List<SpiderLog> selectSpiderLogList(SpiderLog spiderLog);

    /**
     * 新增上榜抓取日志
     * 
     * @param spiderLog 上榜抓取日志
     * @return 结果
     */
    public int insertSpiderLog(SpiderLog spiderLog);

    /**
     * 修改上榜抓取日志
     * 
     * @param spiderLog 上榜抓取日志
     * @return 结果
     */
    public int updateSpiderLog(SpiderLog spiderLog);

    /**
     * 批量删除上榜抓取日志
     * 
     * @param ids 需要删除的上榜抓取日志主键集合
     * @return 结果
     */
    public int deleteSpiderLogByIds(String ids);

    /**
     * 删除上榜抓取日志信息
     * 
     * @param id 上榜抓取日志主键
     * @return 结果
     */
    public int deleteSpiderLogById(Long id);
}
