package com.small.js.web.service.impl;

import com.small.common.core.text.Convert;
import com.small.js.web.domain.SpiderLog;
import com.small.js.web.mapper.SpiderLogMapper;
import com.small.js.web.service.ISpiderLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 上榜抓取日志Service业务层处理
 * 
 * @author small-rose
 * @date 2022-08-19
 */
@Service
public class SpiderLogServiceImpl implements ISpiderLogService 
{
    @Autowired
    private SpiderLogMapper spiderLogMapper;

    /**
     * 查询上榜抓取日志
     * 
     * @param id 上榜抓取日志主键
     * @return 上榜抓取日志
     */
    @Override
    public SpiderLog selectSpiderLogById(Long id)
    {
        return spiderLogMapper.selectSpiderLogById(id);
    }

    /**
     * 查询上榜抓取日志列表
     * 
     * @param spiderLog 上榜抓取日志
     * @return 上榜抓取日志
     */
    @Override
    public List<SpiderLog> selectSpiderLogList(SpiderLog spiderLog)
    {
        return spiderLogMapper.selectSpiderLogList(spiderLog);
    }

    /**
     * 新增上榜抓取日志
     * 
     * @param spiderLog 上榜抓取日志
     * @return 结果
     */
    @Override
    public int insertSpiderLog(SpiderLog spiderLog)
    {
        return spiderLogMapper.insertSpiderLog(spiderLog);
    }

    /**
     * 修改上榜抓取日志
     * 
     * @param spiderLog 上榜抓取日志
     * @return 结果
     */
    @Override
    public int updateSpiderLog(SpiderLog spiderLog)
    {
        return spiderLogMapper.updateSpiderLog(spiderLog);
    }

    /**
     * 批量删除上榜抓取日志
     * 
     * @param ids 需要删除的上榜抓取日志主键
     * @return 结果
     */
    @Override
    public int deleteSpiderLogByIds(String ids)
    {
        return spiderLogMapper.deleteSpiderLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除上榜抓取日志信息
     * 
     * @param id 上榜抓取日志主键
     * @return 结果
     */
    @Override
    public int deleteSpiderLogById(Long id)
    {
        return spiderLogMapper.deleteSpiderLogById(id);
    }
}
