package com.small.js.web.service.impl;

import com.small.common.core.text.Convert;
import com.small.js.web.domain.ArticleRank;
import com.small.js.web.mapper.ArticleRankMapper;
import com.small.js.web.service.IArticleRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 上榜数据Service业务层处理
 * 
 * @author small-rose
 * @date 2022-08-19
 */
@Service
public class ArticleRankServiceImpl implements IArticleRankService
{
    @Autowired
    private ArticleRankMapper articleRankMapper;

    /**
     * 查询上榜数据
     * 
     * @param id 上榜数据主键
     * @return 上榜数据
     */
    @Override
    public ArticleRank selectArticleRankById(Long id)
    {
        return articleRankMapper.selectArticleRankById(id);
    }

    /**
     * 查询上榜数据列表
     * 
     * @param articleRank 上榜数据
     * @return 上榜数据
     */
    @Override
    public List<ArticleRank> selectArticleRankList(ArticleRank articleRank)
    {
        return articleRankMapper.selectArticleRankList(articleRank);
    }

    /**
     * 新增上榜数据
     * 
     * @param articleRank 上榜数据
     * @return 结果
     */
    @Override
    public int insertArticleRank(ArticleRank articleRank)
    {
        return articleRankMapper.insertArticleRank(articleRank);
    }

    /**
     * 修改上榜数据
     * 
     * @param articleRank 上榜数据
     * @return 结果
     */
    @Override
    public int updateArticleRank(ArticleRank articleRank)
    {
        return articleRankMapper.updateArticleRank(articleRank);
    }

    /**
     * 批量删除上榜数据
     * 
     * @param ids 需要删除的上榜数据主键
     * @return 结果
     */
    @Override
    public int deleteArticleRankByIds(String ids)
    {
        return articleRankMapper.deleteArticleRankByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除上榜数据信息
     * 
     * @param id 上榜数据主键
     * @return 结果
     */
    @Override
    public int deleteArticleRankById(Long id)
    {
        return articleRankMapper.deleteArticleRankById(id);
    }

    @Override
    public List<String> selectNickNameList(String keyword) {
        return articleRankMapper.selectNickNameList(keyword);
    }
}
