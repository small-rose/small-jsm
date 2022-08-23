package com.small.js.web.mapper;

import com.small.js.web.domain.JUserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 简书用户Mapper接口
 * 
 * @author small-rose
 * @date 2022-08-19
 */
public interface JUserInfoMapper 
{
    /**
     * 查询简书用户
     * 
     * @param id 简书用户主键
     * @return 简书用户
     */
    public JUserInfo selectJUserInfoById(Long id);

    /**
     * 查询简书用户列表
     * 
     * @param jUserInfo 简书用户
     * @return 简书用户集合
     */
    public List<JUserInfo> selectJUserInfoList(JUserInfo jUserInfo);

    /**
     * 新增简书用户
     * 
     * @param jUserInfo 简书用户
     * @return 结果
     */
    public int insertJUserInfo(JUserInfo jUserInfo);

    /**
     * 修改简书用户
     * 
     * @param jUserInfo 简书用户
     * @return 结果
     */
    public int updateJUserInfo(JUserInfo jUserInfo);

    /**
     * 删除简书用户
     * 
     * @param id 简书用户主键
     * @return 结果
     */
    public int deleteJUserInfoById(Long id);

    /**
     * 批量删除简书用户
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJUserInfoByIds(String[] ids);

    /**
     *
     * @param keyword
     * @return
     */
    List<JUserInfo> selectNickNameList(@Param( "keyword" ) String keyword);

    /**
     * 查询
     * @param slug
     * @return
     */
    JUserInfo findBySlug(@Param( "slug" )String slug);

    /**
     * 推荐人列表
     * @return
     */
    List<JUserInfo> findCommenderList();
}
