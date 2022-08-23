package com.small.js.web.service;

import com.small.js.web.domain.ArticleRank;

import java.util.List;


/**
 * 上榜数据Service接口
 * 
 * @author small-rose
 * @date 2022-08-19
 */
public interface IArticleRankService 
{
    /**
     * 查询上榜数据
     * 
     * @param id 上榜数据主键
     * @return 上榜数据
     */
    public ArticleRank selectArticleRankById(Long id);

    /**
     * 查询上榜数据列表
     * 
     * @param articleRank 上榜数据
     * @return 上榜数据集合
     */
    public List<ArticleRank> selectArticleRankList(ArticleRank articleRank);

    /**
     * 新增上榜数据
     * 
     * @param articleRank 上榜数据
     * @return 结果
     */
    public int insertArticleRank(ArticleRank articleRank);

    /**
     * 修改上榜数据
     * 
     * @param articleRank 上榜数据
     * @return 结果
     */
    public int updateArticleRank(ArticleRank articleRank);

    /**
     * 批量删除上榜数据
     * 
     * @param ids 需要删除的上榜数据主键集合
     * @return 结果
     */
    public int deleteArticleRankByIds(String ids);

    /**
     * 删除上榜数据信息
     * 
     * @param id 上榜数据主键
     * @return 结果
     */
    public int deleteArticleRankById(Long id);

    /**
     * 查昵称
     * @param keyword
     * @return
     */
    List<String> selectNickNameList(String keyword);
}
