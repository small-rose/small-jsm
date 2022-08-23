package com.small.js.web.service.impl;

import com.small.common.core.text.Convert;
import com.small.js.config.IDService;
import com.small.js.web.domain.JUserInfo;
import com.small.js.web.mapper.JUserInfoMapper;
import com.small.js.web.service.IJUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 简书用户Service业务层处理
 * 
 * @author small-rose
 * @date 2022-08-19
 */
@Service
public class JUserInfoServiceImpl implements IJUserInfoService 
{
    @Autowired
    private JUserInfoMapper jUserInfoMapper;
    @Autowired
    private IDService idService;
    /**
     * 查询简书用户
     * 
     * @param id 简书用户主键
     * @return 简书用户
     */
    @Override
    public JUserInfo selectJUserInfoById(Long id)
    {
        return jUserInfoMapper.selectJUserInfoById(id);
    }

    /**
     * 查询简书用户列表
     * 
     * @param jUserInfo 简书用户
     * @return 简书用户
     */
    @Override
    public List<JUserInfo> selectJUserInfoList(JUserInfo jUserInfo)
    {
        return jUserInfoMapper.selectJUserInfoList(jUserInfo);
    }

    /**
     * 新增简书用户
     * 
     * @param jUserInfo 简书用户
     * @return 结果
     */
    @Override
    public int insertJUserInfo(JUserInfo jUserInfo)
    {
        jUserInfo.setId(idService.getNextId());
        return jUserInfoMapper.insertJUserInfo(jUserInfo);
    }

    /**
     * 修改简书用户
     * 
     * @param jUserInfo 简书用户
     * @return 结果
     */
    @Override
    public int updateJUserInfo(JUserInfo jUserInfo)
    {
        return jUserInfoMapper.updateJUserInfo(jUserInfo);
    }

    /**
     * 批量删除简书用户
     * 
     * @param ids 需要删除的简书用户主键
     * @return 结果
     */
    @Override
    public int deleteJUserInfoByIds(String ids)
    {
        return jUserInfoMapper.deleteJUserInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除简书用户信息
     * 
     * @param id 简书用户主键
     * @return 结果
     */
    @Override
    public int deleteJUserInfoById(Long id)
    {
        return jUserInfoMapper.deleteJUserInfoById(id);
    }

    @Override
    public List<JUserInfo> selectNickNameList(String keyword) {
        return jUserInfoMapper.selectNickNameList(keyword);
    }

    @Override
    public void addCheckUser(JUserInfo user) {
        JUserInfo jsUser = jUserInfoMapper.findBySlug(user.getSlug());
        if (jsUser==null){
            user.setPrecommender(0L);
            user.setId(idService.getNextId());
            jUserInfoMapper.insertJUserInfo(user);
        }else{
            JUserInfo record = new JUserInfo();
            record.setId(jsUser.getId());
            record.setPrecommender(0L);
            record.setNickName(user.getNickName());
            record.setNickNamePy(user.getNickNamePy());
            jUserInfoMapper.updateJUserInfo(record);
        }

    }

    @Override
    public List<JUserInfo> findCommenderList() {
        return jUserInfoMapper.findCommenderList();
    }
}
