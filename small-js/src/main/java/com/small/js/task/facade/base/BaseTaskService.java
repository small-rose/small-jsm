package com.small.js.task.facade.base;


import com.small.js.constans.JSConstants;
import com.small.js.util.NetUtils;
import kong.unirest.UnirestException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/8/14 16:04
 * @version: v1.0
 */
@Slf4j
@Data
public abstract class BaseTaskService {

    protected String subjectId ;

    protected Map<String, String> headers ;
    protected Map<String,String> cookies;


    protected void loadHeaderCookies(){
        Connection.Response response = NetUtils.getResponse(JSConstants.REFERURL);
        headers = response.headers();
        cookies = response.cookies();

    }
    /**
     * 取专题URL
     * @return
     */
    protected String getSubjectUrl(){
        return JSConstants.URL_ZHUAN_TI_REG.replace(JSConstants.REPLACE_ZT_KEY, subjectId);
    }

    /**
     * 取专题动态 URL
     * @return
     */
    protected String gettTimeLineDefaultUrl(String userSlug){
        return JSConstants.URL_TIME_LINE_DEFAULT_REG.replace(JSConstants.REPLACE_SLUG_KEY, userSlug);
    }
    /**
     * 取专题动态 URL
     * @return
     */
    protected String gettTimeLineUrl(String userSlug, String maxId){
        return JSConstants.URL_TIME_LINE_REG.replace(JSConstants.REPLACE_SLUG_KEY, userSlug).replace(JSConstants.REPLACE_MAXID_KEY, maxId);
    }

    protected Document getPage(String url){
        headers.put("Content-Type", "text/html; charset=utf-8");
        log.info("请求headers头：" +headers);

        log.info("请求cookies头：" +cookies);
        Document document = NetUtils.getDocument(url, JSConstants.REFERURL, headers, cookies);
        return document ;
    }


    protected abstract void start(String subjectId, int start, int end) throws UnirestException, ParseException;

    protected abstract <T> List<T> parseData(Document zhuanTi, String subjectId) throws UnirestException, ParseException;


    /**
     * 取 评论 URL
     * @return
     */
    protected String getCommentUrl(String noteId){
        return JSConstants.URL_COMMENTS_REG.replace(JSConstants.REPLACE_NOTE_KEY,  noteId);
    }
    /**
     * 取 评论2 URL
     * @return
     */
    protected String getComment2Url(String noteId, String commentCount){
        String replace = JSConstants.URL_COMMENTS_2_REG.replace(JSConstants.REPLACE_NOTE_KEY, noteId);
        replace = replace.replace(JSConstants.REPLACE_COUNT_KEY, commentCount);
        return replace;
    }
    /**
     * 取 赞赏 URL
     * @return
     */
    protected String getRewardUrl(String noteId){
        return JSConstants.URL_REWARD_REG.replace(JSConstants.REPLACE_NOTE_KEY, noteId);
    }
}
