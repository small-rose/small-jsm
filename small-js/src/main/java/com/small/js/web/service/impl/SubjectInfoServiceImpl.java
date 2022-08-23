package com.small.js.web.service.impl;

import com.small.common.core.text.Convert;
import com.small.js.config.IDService;
import com.small.js.web.domain.SubjectInfo;
import com.small.js.web.mapper.SubjectInfoMapper;
import com.small.js.web.service.ISubjectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 专题信息Service业务层处理
 * 
 * @author small-rose
 * @date 2022-08-19
 */
@Service
public class SubjectInfoServiceImpl implements ISubjectInfoService 
{
    @Autowired
    private SubjectInfoMapper subjectInfoMapper;
    @Autowired
    private IDService idService;

    /**
     * 查询专题信息
     * 
     * @param id 专题信息主键
     * @return 专题信息
     */
    @Override
    public SubjectInfo selectSubjectInfoById(Long id)
    {
        return subjectInfoMapper.selectSubjectInfoById(id);
    }

    /**
     * 查询专题信息列表
     * 
     * @param subjectInfo 专题信息
     * @return 专题信息
     */
    @Override
    public List<SubjectInfo> selectSubjectInfoList(SubjectInfo subjectInfo)
    {
        return subjectInfoMapper.selectSubjectInfoList(subjectInfo);
    }

    /**
     * 新增专题信息
     * 
     * @param subjectInfo 专题信息
     * @return 结果
     */
    @Override
    public int insertSubjectInfo(SubjectInfo subjectInfo)
    {
        subjectInfo.setId(idService.getNextId());
        return subjectInfoMapper.insertSubjectInfo(subjectInfo);
    }

    /**
     * 修改专题信息
     * 
     * @param subjectInfo 专题信息
     * @return 结果
     */
    @Override
    public int updateSubjectInfo(SubjectInfo subjectInfo)
    {
        return subjectInfoMapper.updateSubjectInfo(subjectInfo);
    }

    /**
     * 批量删除专题信息
     * 
     * @param ids 需要删除的专题信息主键
     * @return 结果
     */
    @Override
    public int deleteSubjectInfoByIds(String ids)
    {
        return subjectInfoMapper.deleteSubjectInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除专题信息信息
     * 
     * @param id 专题信息主键
     * @return 结果
     */
    @Override
    public int deleteSubjectInfoById(Long id)
    {
        return subjectInfoMapper.deleteSubjectInfoById(id);
    }

    @Override
    public SubjectInfo selectSubjectInfoBySubjectId(String subjectId) {
        return subjectInfoMapper.selectSubjectInfoBySubjectId(subjectId);
    }
}
