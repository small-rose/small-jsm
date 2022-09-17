package com.small.js.web.service;

import com.small.js.web.domain.SubjectDataInfo;
import com.small.js.web.vo.RecommenderVO;

import java.util.List;

/**
 * 专题数据Service接口
 * 
 * @author small-rose
 * @date 2022-08-19
 */
public interface ISubjectDataInfoService 
{
    /**
     * 查询专题数据
     * 
     * @param id 专题数据主键
     * @return 专题数据
     */
    public SubjectDataInfo selectSubjectDataInfoById(Long id);

    /**
     * 查询专题数据列表
     * 
     * @param subjectDataInfo 专题数据
     * @return 专题数据集合
     */
    public List<SubjectDataInfo> selectSubjectDataInfoList(SubjectDataInfo subjectDataInfo, String order);

    /**
     * 新增专题数据
     * 
     * @param subjectDataInfo 专题数据
     * @return 结果
     */
    public int insertSubjectDataInfo(SubjectDataInfo subjectDataInfo);

    /**
     * 修改专题数据
     * 
     * @param subjectDataInfo 专题数据
     * @return 结果
     */
    public int updateSubjectDataInfo(SubjectDataInfo subjectDataInfo);


    /**
     * 为了更新收录时间，以喜欢时间为准
     * @param subjectDataInfo
     * @return
     */
    public int updateSubjectDataInfoBySubjectIdWenSlug(SubjectDataInfo subjectDataInfo);
    /**
     * 批量删除专题数据
     * 
     * @param ids 需要删除的专题数据主键集合
     * @return 结果
     */
    public int deleteSubjectDataInfoByIds(String ids);

    /**
     * 删除专题数据信息
     * 
     * @param id 专题数据主键
     * @return 结果
     */
    public int deleteSubjectDataInfoById(Long id);

    /**
     * 搜昵称
     * @param keyword
     * @return
     */
    List<String> selectNickNameList(String keyword);

    /**
     * 更新推荐人
     * @param recommenderVO
     * @return
     */
    int updateRecomenderInfo(RecommenderVO recommenderVO);


    void saveData(SubjectDataInfo wenStory);

}
