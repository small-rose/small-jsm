package com.small.js.task.service;

import com.small.js.config.IDService;
import com.small.js.web.domain.ArticleRank;
import com.small.js.web.mapper.ArticleRankMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project : small
 * @Author : zhangzongyuan
 * @Description : [ ArticleRankService ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/8/19 9:40
 * @Version ： 1.0
 **/
@Slf4j
@Service
public class ArticleRankService {

    @Autowired
    private IDService idService ;
    @Autowired
    private ArticleRankMapper articleRankMapper ;

    public Long countByDate(String rankDate) {
        return articleRankMapper.countByDate(rankDate);
    }

    public Integer deleteByDate(String rankDate) {
        return articleRankMapper.deleteByDate(rankDate);
    }

    public void saveAll(List<ArticleRank> list) {
        articleRankMapper.insertArticleRankList(list);
    }

    public List<ArticleRank> findBySlug(String slug) {
        return articleRankMapper.findBySlug(slug);
    }

    public void updateNickNameBySlug(String slug, String newNickName, String pinyinString) {
        articleRankMapper.updateNickNameBySlug(slug, newNickName, pinyinString);
    }
}
