package com.small.js.web.service;

import com.small.js.web.domain.SubjectInfo;

import java.util.List;

/**
 * 专题信息Service接口
 * 
 * @author small-rose
 * @date 2022-08-19
 */
public interface ISubjectInfoService 
{
    /**
     * 查询专题信息
     * 
     * @param id 专题信息主键
     * @return 专题信息
     */
    public SubjectInfo selectSubjectInfoById(Long id);

    /**
     * 查询专题信息列表
     * 
     * @param subjectInfo 专题信息
     * @return 专题信息集合
     */
    public List<SubjectInfo> selectSubjectInfoList(SubjectInfo subjectInfo);

    /**
     * 新增专题信息
     * 
     * @param subjectInfo 专题信息
     * @return 结果
     */
    public int insertSubjectInfo(SubjectInfo subjectInfo);

    /**
     * 修改专题信息
     * 
     * @param subjectInfo 专题信息
     * @return 结果
     */
    public int updateSubjectInfo(SubjectInfo subjectInfo);

    /**
     * 批量删除专题信息
     * 
     * @param ids 需要删除的专题信息主键集合
     * @return 结果
     */
    public int deleteSubjectInfoByIds(String ids);

    /**
     * 删除专题信息信息
     * 
     * @param id 专题信息主键
     * @return 结果
     */
    public int deleteSubjectInfoById(Long id);

    /**
     * 查专题
     * @param subjectId
     * @return
     */
    SubjectInfo selectSubjectInfoBySubjectId(String subjectId);
}
