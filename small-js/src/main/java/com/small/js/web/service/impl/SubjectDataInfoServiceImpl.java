package com.small.js.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.small.common.core.text.Convert;
import com.small.common.utils.DateUtils;
import com.small.js.config.IDService;
import com.small.js.web.domain.SubjectDataInfo;
import com.small.js.web.mapper.SubjectDataInfoMapper;
import com.small.js.web.service.ISubjectDataInfoService;
import com.small.js.web.vo.RecommenderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 专题数据Service业务层处理
 * 
 * @author small-rose
 * @date 2022-08-19
 */
@Slf4j
@Service
public class SubjectDataInfoServiceImpl implements ISubjectDataInfoService 
{
    @Autowired
    private SubjectDataInfoMapper subjectDataInfoMapper;
    @Autowired
    private IDService idService;

    /**
     * 查询专题数据
     * 
     * @param id 专题数据主键
     * @return 专题数据
     */
    @Override
    public SubjectDataInfo selectSubjectDataInfoById(Long id)
    {
        return subjectDataInfoMapper.selectSubjectDataInfoById(id);
    }

    /**
     * 查询专题数据列表
     * 
     * @param subjectDataInfo 专题数据
     * @return 专题数据
     */
    @Override
    public List<SubjectDataInfo> selectSubjectDataInfoList(SubjectDataInfo subjectDataInfo, String order)
    {
        return subjectDataInfoMapper.selectSubjectDataInfoList(subjectDataInfo, order);
    }

    /**
     * 新增专题数据
     * 
     * @param subjectDataInfo 专题数据
     * @return 结果
     */
    @Override
    public int insertSubjectDataInfo(SubjectDataInfo subjectDataInfo)
    {
        return subjectDataInfoMapper.insertSubjectDataInfo(subjectDataInfo);
    }

    /**
     * 修改专题数据
     * 
     * @param subjectDataInfo 专题数据
     * @return 结果
     */
    @Override
    public int updateSubjectDataInfo(SubjectDataInfo subjectDataInfo)
    {
        subjectDataInfo.setUpdateTime(DateUtils.getNowDate());
        return subjectDataInfoMapper.updateSubjectDataInfo(subjectDataInfo);
    }

    public int updateSubjectDataInfoBySubjectIdWenSlug(SubjectDataInfo subjectDataInfo){
        return subjectDataInfoMapper.updateSubjectDataInfoBySubjectIdWenSlug(subjectDataInfo);
    }
    /**
     * 批量删除专题数据
     * 
     * @param ids 需要删除的专题数据主键
     * @return 结果
     */
    @Override
    public int deleteSubjectDataInfoByIds(String ids)
    {
        return subjectDataInfoMapper.deleteSubjectDataInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除专题数据信息
     * 
     * @param id 专题数据主键
     * @return 结果
     */
    @Override
    public int deleteSubjectDataInfoById(Long id)
    {
        return subjectDataInfoMapper.deleteSubjectDataInfoById(id);
    }

    @Override
    public List<String> selectNickNameList(String keyword) {
        return subjectDataInfoMapper.selectNickNameByKeyword(keyword);
    }

    @Override
    public int updateRecomenderInfo(RecommenderVO recommenderVO) {
        return subjectDataInfoMapper.updateRecomenderInfo(recommenderVO);
    }

    @Override
    public void saveData(SubjectDataInfo subjectDataInfo) {

        SubjectDataInfo ztData = subjectDataInfoMapper.selectSubjectDataInfoByWenId(subjectDataInfo.getWenId(), subjectDataInfo.getSubjectId());
        if (ztData==null){
            subjectDataInfo.setId(idService.getNextId());
            log.info("add data : {} ", JSON.toJSONString(subjectDataInfo));
            subjectDataInfoMapper.insertSubjectDataInfo(subjectDataInfo);
        }else{
            SubjectDataInfo record = new SubjectDataInfo();
            record.setId(ztData.getId());
            record.setRewards(subjectDataInfo.getRewards());
            record.setComments(subjectDataInfo.getComments());
            record.setNickName(subjectDataInfo.getNickName());
            record.setRecommender(subjectDataInfo.getRecommender());
            record.setRecommenderSlug(subjectDataInfo.getRecommenderSlug());
            record.setUpdateTime(new Date());
            log.info("update data : {} ", JSON.toJSONString(subjectDataInfo));
            subjectDataInfoMapper.updateSubjectDataInfo(record);
        }
    }

}
