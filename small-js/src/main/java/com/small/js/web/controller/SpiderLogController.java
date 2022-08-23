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
import com.small.js.web.domain.SpiderLog;
import com.small.js.web.service.ISpiderLogService;
import com.small.common.core.controller.BaseController;
import com.small.common.core.domain.AjaxResult;
import com.small.common.utils.poi.ExcelUtil;
import com.small.common.core.page.TableDataInfo;

/**
 * 上榜抓取日志Controller
 * 
 * @author small-rose
 * @date 2022-08-19
 */
@Controller
@RequestMapping("/js/log")
public class SpiderLogController extends BaseController
{
    private String prefix = "js/log";

    @Autowired
    private ISpiderLogService spiderLogService;

    @RequiresPermissions("js:log:view")
    @GetMapping()
    public String log()
    {
        return prefix + "/log";
    }

    /**
     * 查询上榜抓取日志列表
     */
    @RequiresPermissions("js:log:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SpiderLog spiderLog)
    {
        startPage();
        List<SpiderLog> list = spiderLogService.selectSpiderLogList(spiderLog);
        return getDataTable(list);
    }

    /**
     * 导出上榜抓取日志列表
     */
    @RequiresPermissions("js:log:export")
    @Log(title = "上榜抓取日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SpiderLog spiderLog)
    {
        List<SpiderLog> list = spiderLogService.selectSpiderLogList(spiderLog);
        ExcelUtil<SpiderLog> util = new ExcelUtil<SpiderLog>(SpiderLog.class);
        return util.exportExcel(list, "上榜抓取日志数据");
    }

    /**
     * 新增上榜抓取日志
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存上榜抓取日志
     */
    @RequiresPermissions("js:log:add")
    @Log(title = "上榜抓取日志", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SpiderLog spiderLog)
    {
        return toAjax(spiderLogService.insertSpiderLog(spiderLog));
    }

    /**
     * 修改上榜抓取日志
     */
    @RequiresPermissions("js:log:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SpiderLog spiderLog = spiderLogService.selectSpiderLogById(id);
        mmap.put("spiderLog", spiderLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存上榜抓取日志
     */
    @RequiresPermissions("js:log:edit")
    @Log(title = "上榜抓取日志", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SpiderLog spiderLog)
    {
        return toAjax(spiderLogService.updateSpiderLog(spiderLog));
    }

    /**
     * 删除上榜抓取日志
     */
    @RequiresPermissions("js:log:remove")
    @Log(title = "上榜抓取日志", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(spiderLogService.deleteSpiderLogByIds(ids));
    }
}
