package com.small.js.web.service;

import com.small.js.web.vo.ReportData;

import java.util.List;

/**
 * 简书用户Service接口
 * 
 * @author small-rose
 * @date 2022-08-19
 */
public interface IJsReportService
{

    List<Integer> selectCurrentWeekList();

    ReportData selectCurrentMonthList();

    List<List<Object>> selectRecommenderLastDaysList();
}
