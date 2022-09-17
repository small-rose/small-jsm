package com.small.js.web.mapper;

import com.small.js.web.domain.SubjectDataInfo;
import com.small.js.web.vo.RecommenderVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 专题数据Mapper接口
 * 
 * @author small-rose
 * @date 2022-08-19
 */
public interface SubjectDataInfoMapper 
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
    public List<SubjectDataInfo> selectSubjectDataInfoList(@Param("subject") SubjectDataInfo subjectDataInfo, @Param("order") String order);

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
     * 修改专题数据
     *
     * @param subjectDataInfo 专题数据
     * @return 结果
     */
    public int updateSubjectDataInfoBySubjectIdWenSlug(SubjectDataInfo subjectDataInfo);

    /**
     * 删除专题数据
     * 
     * @param id 专题数据主键
     * @return 结果
     */
    public int deleteSubjectDataInfoById(Long id);

    /**
     * 批量删除专题数据
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSubjectDataInfoByIds(String[] ids);

    /**
     * 搜昵称
     * @param keyword
     * @return
     */
    List<String> selectNickNameByKeyword(@Param( "keyword" ) String keyword);

    /**
     * 更新推荐人
     * @param recommenderVO
     * @return
     */
    int updateRecomenderInfo(@Param("recommenderVO" )RecommenderVO recommenderVO);

    /**
     *
     * @param wenId
     * @return
     */
    SubjectDataInfo selectSubjectDataInfoByWenId(@Param("wenId") String wenId,@Param("subjectId")String subjectId);
}
