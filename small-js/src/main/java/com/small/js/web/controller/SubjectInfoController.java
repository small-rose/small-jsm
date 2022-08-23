package com.small.js.web.controller;

import com.small.common.annotation.Log;
import com.small.common.core.controller.BaseController;
import com.small.common.core.domain.AjaxResult;
import com.small.common.core.page.TableDataInfo;
import com.small.common.enums.BusinessType;
import com.small.common.utils.poi.ExcelUtil;
import com.small.js.web.domain.SubjectInfo;
import com.small.js.web.service.ISubjectInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 专题信息Controller
 * 
 * @author small-rose
 * @date 2022-08-19
 */
@Controller
@RequestMapping("/js/subjectInfo")
public class SubjectInfoController extends BaseController
{
    private String prefix = "js/subjectInfo";

    @Autowired
    private ISubjectInfoService subjectInfoService;

    @RequiresPermissions("js:subjectInfo:view")
    @GetMapping()
    public String subjectInfo()
    {
        return prefix + "/subjectInfo";
    }

    /**
     * 查询专题信息列表
     */
    @RequiresPermissions("js:subjectInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SubjectInfo subjectInfo)
    {
        startPage();
        List<SubjectInfo> list = subjectInfoService.selectSubjectInfoList(subjectInfo);
        return getDataTable(list);
    }

    /**
     * 导出专题信息列表
     */
    @RequiresPermissions("js:subjectInfo:export")
    @Log(title = "专题信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SubjectInfo subjectInfo)
    {
        List<SubjectInfo> list = subjectInfoService.selectSubjectInfoList(subjectInfo);
        ExcelUtil<SubjectInfo> util = new ExcelUtil<SubjectInfo>(SubjectInfo.class);
        return util.exportExcel(list, "专题信息数据");
    }

    /**
     * 新增专题信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存专题信息
     */
    @RequiresPermissions("js:subjectInfo:add")
    @Log(title = "专题信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SubjectInfo subjectInfo)
    {
        return toAjax(subjectInfoService.insertSubjectInfo(subjectInfo));
    }

    /**
     * 修改专题信息
     */
    @RequiresPermissions("js:subjectInfo:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SubjectInfo subjectInfo = subjectInfoService.selectSubjectInfoById(id);
        mmap.put("subjectInfo", subjectInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存专题信息
     */
    @RequiresPermissions("js:subjectInfo:edit")
    @Log(title = "专题信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SubjectInfo subjectInfo)
    {
        return toAjax(subjectInfoService.updateSubjectInfo(subjectInfo));
    }

    /**
     * 删除专题信息
     */
    @RequiresPermissions("js:subjectInfo:remove")
    @Log(title = "专题信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(subjectInfoService.deleteSubjectInfoByIds(ids));
    }
}
