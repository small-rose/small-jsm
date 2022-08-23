package com.small.js.web.controller;

import com.small.common.annotation.Log;
import com.small.common.core.controller.BaseController;
import com.small.common.core.domain.AjaxResult;
import com.small.common.core.page.TableDataInfo;
import com.small.common.enums.BusinessType;
import com.small.common.utils.poi.ExcelUtil;
import com.small.js.web.domain.JUserInfo;
import com.small.js.web.domain.SubjectDataInfo;
import com.small.js.web.service.IJUserInfoService;
import com.small.js.web.service.ISubjectDataInfoService;
import com.small.js.web.vo.RecommenderVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专题数据Controller
 * 
 * @author small-rose
 * @date 2022-08-19
 */
@Controller
@RequestMapping("/js/subjectDataInfo")
public class SubjectDataInfoController extends BaseController
{
    private String prefix = "js/subjectDataInfo";

    @Autowired
    private ISubjectDataInfoService subjectDataInfoService;
    @Autowired
    private IJUserInfoService userInfoService;

    @RequiresPermissions("js:subjectDataInfo:view")
    @GetMapping()
    public String subjectDataInfo()
    {
        return prefix + "/subjectDataInfo";
    }

    /**
     * 查询专题数据列表
     */
    @RequiresPermissions("js:subjectDataInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SubjectDataInfo subjectDataInfo)
    {
        startPage();
        List<SubjectDataInfo> list = subjectDataInfoService.selectSubjectDataInfoList(subjectDataInfo, "desc");
        return getDataTable(list);
    }

    /**
     * 导出专题数据列表
     */
    @RequiresPermissions("js:subjectDataInfo:export")
    @Log(title = "专题数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SubjectDataInfo subjectDataInfo)
    {
        List<SubjectDataInfo> list = subjectDataInfoService.selectSubjectDataInfoList(subjectDataInfo, "asc");
        ExcelUtil<SubjectDataInfo> util = new ExcelUtil<>(SubjectDataInfo.class);
        //return util.exportExcel(list, "专题收录数据", list.get(0).getShouDate()+"专题收录", list.get(0).getShouDate()+"专题收录");
        return util.exportExcel(list, "专题收录数据", list.get(0).getShouDate()+"专题收录");
    }

    /**
     * 新增专题数据
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存专题数据
     */
    @RequiresPermissions("js:subjectDataInfo:add")
    @Log(title = "专题数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SubjectDataInfo subjectDataInfo)
    {
        return toAjax(subjectDataInfoService.insertSubjectDataInfo(subjectDataInfo));
    }

    /**
     * 修改专题数据
     */
    @RequiresPermissions("js:subjectDataInfo:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SubjectDataInfo subjectDataInfo = subjectDataInfoService.selectSubjectDataInfoById(id);
        mmap.put("subjectDataInfo", subjectDataInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存专题数据
     */
    @RequiresPermissions("js:subjectDataInfo:edit")
    @Log(title = "专题数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SubjectDataInfo subjectDataInfo)
    {
        return toAjax(subjectDataInfoService.updateSubjectDataInfo(subjectDataInfo));
    }

    /**
     * 删除专题数据
     */
    @RequiresPermissions("js:subjectDataInfo:remove")
    @Log(title = "专题数据", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(subjectDataInfoService.deleteSubjectDataInfoByIds(ids));
    }
    /**
     * 更新推荐人专题数据
     */
    @RequiresPermissions("js:subjectDataInfo:updateRecommender")
    @Log(title = "更新专题推荐人", businessType = BusinessType.UPDATE)
    @PostMapping( "/updateRecommender")
    @ResponseBody
    public AjaxResult updateRecommender(RecommenderVO recommenderVO)
    {
        try {
            subjectDataInfoService.updateRecomenderInfo(recommenderVO);
            return AjaxResult.success();
        }catch (Exception e){
            e.printStackTrace();
           return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 查询字典详细
     */
    //@RequiresPermissions("js:subjectDataInfo:")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap)
    {
        SubjectDataInfo subjectDataInfo = subjectDataInfoService.selectSubjectDataInfoById(id);
        mmap.put("subjectDataInfo", subjectDataInfo);
        return prefix + "/viewComments";
    }

    @GetMapping("/nickName")
    @ResponseBody
    public AjaxResult nickName(String keyword)
    {
        Map<String, Object> result = new HashMap<>();
        List<String> list = subjectDataInfoService.selectNickNameList(keyword);
        result.put("result", list);
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.putAll(result);
        return ajaxResult;
    }


    @GetMapping("/recommender")
    @ResponseBody
    public AjaxResult recommender(String keyword)
    {
        Map<String, Object> result = new HashMap<>();
        List<JUserInfo> list = userInfoService.selectNickNameList(keyword);
        result.put("result", list);
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.putAll(result);
        return ajaxResult;
    }
}
