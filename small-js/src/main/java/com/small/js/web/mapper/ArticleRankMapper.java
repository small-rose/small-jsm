package com.small.js.web.mapper;

import com.small.js.web.domain.ArticleRank;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 上榜数据Mapper接口
 * 
 * @author small-rose
 * @date 2022-08-19
 */
public interface ArticleRankMapper 
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
     * 删除上榜数据
     * 
     * @param id 上榜数据主键
     * @return 结果
     */
    public int deleteArticleRankById(Long id);

    /**
     * 批量删除上榜数据
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteArticleRankByIds(String[] ids);

    /**
     * 统计排名数据-正常是100
     * @param rankDate
     * @return
     */
    Long countByDate(@Param("rankDate") String rankDate);
    /**
     * 删除排名数据-正常是100
     * @param rankDate
     * @return
     */
    Integer deleteByDate(String rankDate);

    /**
     * 批量写入
     * @param rankList
     */
    void insertArticleRankList(@Param("rankList") List<ArticleRank> rankList);
//    void insertArticleRankList(@Param("rank")ArticleRank rankList);

    /**
     *  一篇文章可以重复上榜
     * @param slug
     * @return
     */
    List<ArticleRank> findBySlug(String slug);

    /**
     * 更新昵称
     * @param slug
     * @param newNickName
     * @param pinyinString
     */
    void updateNickNameBySlug(@Param("slug") String slug, @Param("newNickName") String newNickName, @Param("pinyinString") String pinyinString);

    /**
     *
     * @param keyword
     * @return
     */
    List<String> selectNickNameList(@Param("keyword") String keyword);
}
