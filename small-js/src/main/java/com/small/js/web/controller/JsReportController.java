package com.small.js.web.controller;

import com.small.common.core.domain.AjaxResult;
import com.small.js.web.callable.JsReportCallableService;
import com.small.js.web.service.IJsReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/8/20 19:35
 * @version: v1.0
 */
@Controller
@RequestMapping("/main/report")
public class JsReportController {

    @Autowired
    private IJsReportService jsReportService;

    @Autowired
    private JsReportCallableService jsReportCallableService ;

    @PostMapping("/allData")
    @ResponseBody
    public AjaxResult allData()
    {
        Map<String, Object> result = new HashMap<>();



        //List<Integer> list = jsReportService.selectCurrentWeekList();
        //result.put("weekData", list);
        //ReportData list2 = jsReportService.selectCurrentMonthList();

        //result.put("monthData", list2);

        Map<String, Object>  reports = null;
        AjaxResult ajaxResult = AjaxResult.success();
        try {
            reports = jsReportCallableService.getReports();

            ajaxResult.putAll(reports);
        } catch (Exception e) {
            e.printStackTrace();
            AjaxResult.error("查询出错");
        }

        return ajaxResult;
    }
}
