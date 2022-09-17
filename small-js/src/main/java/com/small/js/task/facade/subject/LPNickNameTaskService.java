package com.small.js.task.facade.subject;

import com.small.js.task.facade.base.BaseTaskService;
import com.small.js.web.domain.JUserInfo;
import com.small.js.web.service.IJUserInfoService;
import kong.unirest.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/8/27 14:28
 * @version: v1.0
 */
@Slf4j
@Service
public class LPNickNameTaskService  extends BaseTaskService {

    @Autowired
    private IJUserInfoService jUserInfoService ;

    @Override
    public void start(String subjectId, int start, int end) throws UnirestException {

        loadHeaderCookies();

        JUserInfo juser = new JUserInfo();
        juser.setPrecommender(1L);
        List<JUserInfo> jUserInfos = jUserInfoService.selectJUserInfoList(juser);
        if (CollectionUtils.isEmpty(jUserInfos)){
            log.info("没有找到推荐人，更新理事会人员昵称结束");
            return;
        }
        jUserInfos.stream().filter(u-> StringUtils.hasText(u.getSlug())).forEach(u->{
            String url = "https://www.jianshu.com/u/"+u.getSlug();
            Document document = getPage(url);
            Element element = document.getElementsByTag("script").get(1);
            System.out.println(element);
            System.out.println("=======================================");
            String substring = element.data().substring(element.data().indexOf("=") + 1, element.data().indexOf("};")+1);
            substring = substring.replace("// 'GET' 和 'POST'","");
            substring = substring.replace("// 'https://www.taobao.com/detail'","");
            substring = substring.replace(",}", "}");
            System.out.println(substring);

            Elements select = document.select("div.main-top div.title a.name");
            System.out.println(select);
            String nickName = document.select("div.main-top div.title a.name").text();
            return;
           /* if (StringUtils.hasText(nickName) && !u.getNickName().equals(nickName)){
                JUserInfo record = new JUserInfo();
                record.setId(u.getId());
                record.setNickName(nickName);
                record.setNickNamePy(PinYinSmallUtil.getPinYin(nickName));
                jUserInfoService.updateJUserInfo(record);
            }*/

        });
    }

    @Override
    protected List<JUserInfo> parseData(Document document, String odlNickName) throws UnirestException {
        return null;
    }
}
