package com.small.js.web.controller;

import com.small.common.annotation.Log;
import com.small.common.core.controller.BaseController;
import com.small.common.core.domain.AjaxResult;
import com.small.common.core.page.TableDataInfo;
import com.small.common.enums.BusinessType;
import com.small.common.utils.poi.ExcelUtil;
import com.small.js.web.domain.ArticleRank;
import com.small.js.web.service.IArticleRankService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 上榜数据Controller
 * 
 * @author small-rose
 * @date 2022-08-19
 */
@Controller
@RequestMapping("/js/rank")
public class ArticleRankController extends BaseController
{
    private String prefix = "js/rank";

    @Autowired
    private IArticleRankService articleRankService;

    @RequiresPermissions("js:rank:view")
    @GetMapping()
    public String rank()
    {
        return prefix + "/rank";
    }

    /**
     * 查询上榜数据列表
     */
    @RequiresPermissions("js:rank:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ArticleRank articleRank)
    {
        startPage();
        List<ArticleRank> list = articleRankService.selectArticleRankList(articleRank);
        return getDataTable(list);
    }

    /**
     * 导出上榜数据列表
     */
    @RequiresPermissions("js:rank:export")
    @Log(title = "上榜数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ArticleRank articleRank)
    {
        List<ArticleRank> list = articleRankService.selectArticleRankList(articleRank);
        ExcelUtil<ArticleRank> util = new ExcelUtil<ArticleRank>(ArticleRank.class);
        return util.exportExcel(list, "上榜数据数据");
    }


    @GetMapping("/nickName")
    @ResponseBody
    public AjaxResult nickName(String keyword)
    {
        Map<String, Object> result = new HashMap<>();
        List<String> list = articleRankService.selectNickNameList(keyword);
        result.put("result", list);
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.putAll(result);
        return ajaxResult;
    }

    /**
     * 新增上榜数据
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存上榜数据
     */
    @RequiresPermissions("js:rank:add")
    @Log(title = "上榜数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ArticleRank articleRank)
    {
        return toAjax(articleRankService.insertArticleRank(articleRank));
    }

    /**
     * 修改上榜数据
     */
    @RequiresPermissions("js:rank:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ArticleRank articleRank = articleRankService.selectArticleRankById(id);
        mmap.put("articleRank", articleRank);
        return prefix + "/edit";
    }

    /**
     * 修改保存上榜数据
     */
    @RequiresPermissions("js:rank:edit")
    @Log(title = "上榜数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ArticleRank articleRank)
    {
        return toAjax(articleRankService.updateArticleRank(articleRank));
    }

    /**
     * 删除上榜数据
     */
    @RequiresPermissions("js:rank:remove")
    @Log(title = "上榜数据", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(articleRankService.deleteArticleRankByIds(ids));
    }
}
