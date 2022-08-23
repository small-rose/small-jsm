package com.small.js.web.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.small.common.annotation.Log;
import com.small.common.enums.BusinessType;
import com.small.js.web.domain.JUserInfo;
import com.small.js.web.service.IJUserInfoService;
import com.small.common.core.controller.BaseController;
import com.small.common.core.domain.AjaxResult;
import com.small.common.utils.poi.ExcelUtil;
import com.small.common.core.page.TableDataInfo;

/**
 * 简书用户Controller
 * 
 * @author small-rose
 * @date 2022-08-19
 */
@Controller
@RequestMapping("/js/userInfo")
public class JUserInfoController extends BaseController
{
    private String prefix = "js/userInfo";

    @Autowired
    private IJUserInfoService jUserInfoService;

    @RequiresPermissions("js:userInfo:view")
    @GetMapping()
    public String userInfo()
    {
        return prefix + "/userInfo";
    }

    /**
     * 查询简书用户列表
     */
    @RequiresPermissions("js:userInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(JUserInfo jUserInfo)
    {
        startPage();
        List<JUserInfo> list = jUserInfoService.selectJUserInfoList(jUserInfo);
        return getDataTable(list);
    }

    /**
     * 导出简书用户列表
     */
    @RequiresPermissions("js:userInfo:export")
    @Log(title = "简书用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(JUserInfo jUserInfo)
    {
        List<JUserInfo> list = jUserInfoService.selectJUserInfoList(jUserInfo);
        ExcelUtil<JUserInfo> util = new ExcelUtil<JUserInfo>(JUserInfo.class);
        return util.exportExcel(list, "简书用户数据");
    }

    /**
     * 新增简书用户
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存简书用户
     */
    @RequiresPermissions("js:userInfo:add")
    @Log(title = "简书用户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(JUserInfo jUserInfo)
    {
        return toAjax(jUserInfoService.insertJUserInfo(jUserInfo));
    }

    /**
     * 修改简书用户
     */
    @RequiresPermissions("js:userInfo:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        JUserInfo jUserInfo = jUserInfoService.selectJUserInfoById(id);
        mmap.put("jUserInfo", jUserInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存简书用户
     */
    @RequiresPermissions("js:userInfo:edit")
    @Log(title = "简书用户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(JUserInfo jUserInfo)
    {
        return toAjax(jUserInfoService.updateJUserInfo(jUserInfo));
    }

    /**
     * 删除简书用户
     */
    @RequiresPermissions("js:userInfo:remove")
    @Log(title = "简书用户", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(jUserInfoService.deleteJUserInfoByIds(ids));
    }
}
