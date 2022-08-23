package com.small.js.task.service;

import com.small.js.config.IDService;
import com.small.js.web.domain.SpiderLog;
import com.small.js.web.mapper.SpiderLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @Project : small
 * @Author : zhangzongyuan
 * @Description : [ SpiderLogService ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/8/19 9:16
 * @Version ： 1.0
 **/
@Slf4j
@Service
public class SpiderLogService {

    @Autowired
    private IDService idService ;
    @Autowired
    private SpiderLogMapper spiderLogMapper ;

    public List<SpiderLog> selectByRankDateRankType(String rankDate, String rankType) {
        return spiderLogMapper.selectByRankDateRankType(rankDate, rankType);
    }

    public void saveLog(String rankDate, String result, String rankType) {
        //保存结果

        List<SpiderLog> list = spiderLogMapper.selectByRankDateRankType(rankDate, rankType);
        SpiderLog spiderLog = null;
        if (CollectionUtils.isEmpty(list)){
            spiderLog = new SpiderLog();
            spiderLog.setId(idService.getNextId());
            spiderLog.setRankDate(rankDate);
            spiderLog.setRankType(rankType);
            spiderLog.setExecTime(new Date());
            if (result.startsWith("SUCCESS")){
                spiderLog.setResult("SUCCESS");
                spiderLog.setStatus("S");
            }else{
                spiderLog.setResult(result);
                spiderLog.setStatus("F");
            }
            spiderLog.setId(idService.getNextId());
            spiderLogMapper.insertSpiderLog(spiderLog);
            log.info("抓取日志新增成功！");

        }else{
            spiderLog = list.get(0);
            spiderLog.setExecTime(new Date());
            if (result.startsWith("SUCCESS")){
                spiderLog.setResult("SUCCESS");
                spiderLog.setStatus("S");
            }else{
                spiderLog.setResult(result);
                spiderLog.setStatus("F");
            }

            spiderLogMapper.updateSpiderLog(spiderLog);
            log.info("抓取日志更新成功！");
        }


    }

    public void deleteById(SpiderLog spiderLog) {
        spiderLogMapper.deleteSpiderLogById(spiderLog.getId());
    }

    public void deleteSpiderLog(SpiderLog spiderLog) {
        spiderLogMapper.deleteSpiderLog(spiderLog);
    }

    public List<SpiderLog> selectByRankTypeUpdateResult(String rankType) {
        //
        return spiderLogMapper.selectByRankTypeUpdateResult(rankType);
    }
}
