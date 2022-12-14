package com.small.js.web.service.impl;

import com.small.js.util.SamllDateUtil;
import com.small.js.web.mapper.JsReportMapper;
import com.small.js.web.service.IJsReportService;
import com.small.js.web.vo.ReportData;
import com.small.js.web.vo.ReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 简书用户Service业务层处理
 * 
 * @author small-rose
 * @date 2022-08-19
 */
@Service
public class JsReportServiceImpl implements IJsReportService
{
    @Autowired
    private JsReportMapper jsReportMapper;


    @Override
    public List<Integer> selectCurrentWeekList() {
        LocalDate today = LocalDate.now();
        //LocalDate monday = today.with(TemporalAdjusters.previousOrSame( DayOfWeek.MONDAY));
        //LocalDate sunday = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY ));
        //String start = monday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //String end = sunday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        LocalDate yesterday = today.minusDays(1);
        LocalDate startDay = yesterday.minusDays(6);
        String start = startDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String end = yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<ReportVO> reportVOS = jsReportMapper.selectCurrentWeekList(start, end);
        if (CollectionUtils.isEmpty(reportVOS)){
            return new ArrayList<>(0);
        }

        return reportVOS.stream().map(ReportVO::getCount).collect(Collectors.toList());
    }

    @Override
    public ReportData selectCurrentMonthList() {
        LocalDate today = LocalDate.now().plusDays(1);
        LocalDate firstDay = today.minusDays(31);
        String start = firstDay.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String end = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        LocalDate yyyyMMdd1 = LocalDate.parse(start, DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate yyyyMMdd2 = LocalDate.parse(end, DateTimeFormatter.BASIC_ISO_DATE);
        List<LocalDate> localDates = SamllDateUtil.getsAllDatesInTheDateRange(yyyyMMdd1, yyyyMMdd2);

      /*  String yyyyMM = today.format(DateTimeFormatter.ofPattern("yyyyMM"));
        LocalDate localDate = LocalDate.parse(yyyyMM + "01", DateTimeFormatter.ofPattern("yyyyMMdd"));
        //System.out.println("localDate为：" + localDate);
        Integer first = new Integer(localDate.format(DateTimeFormatter.BASIC_ISO_DATE));
        // System.out.println("firstDay为：" + firstDay);
        int lastNum = today.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
        //System.out.println("lastNum为：" + lastNum);
        List<Integer> month = new ArrayList<>(lastNum);
        for (int i= 1 ; i<= lastNum; i++){
            month.add(first);
            first++;
        }*/
        List<String> datax = localDates.stream().map(l-> l.format(DateTimeFormatter.ofPattern("yyyyMMdd"))).collect(Collectors.toList());
        //List<String> datax = month.stream().map(t -> String.valueOf(t)).collect(Collectors.toList());
        //System.out.println(datax.size());
        //System.out.println(datax);
        List<ReportVO> reportVOS = jsReportMapper.selectCurrentWeekList(start, end);
        if (CollectionUtils.isEmpty(reportVOS)){
            reportVOS = new ArrayList<>();
        }
        Integer[] dataVal = new Integer[datax.size()];
        AtomicInteger count = new AtomicInteger(0);

        Map<String, Integer> dataMap = reportVOS.stream().collect(Collectors.toMap(ReportVO::getShouDateFormat, ReportVO::getCount));
        for (String s : datax) {
            if (dataMap.get(s)!=null) {
                dataVal[count.get()] = dataMap.get(s);
            } else {
                dataVal[count.get()] = null;
            }
            //System.out.println(count.get());
            count.getAndIncrement();
        }
        ReportData reportData = new ReportData();
        reportData.setDatax(datax);
        reportData.setDatay(Arrays.asList(dataVal));
        return reportData;
    }

    @Override
    public List<List<Object>> selectRecommenderLastDaysList() {
        LocalDate today = LocalDate.now();//.minusDays(1);
        LocalDate firstDay = today.minusDays(15);

        String start = firstDay.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String end = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        LocalDate yyyyMMdd1 = LocalDate.parse(start, DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate yyyyMMdd2 = LocalDate.parse(end, DateTimeFormatter.BASIC_ISO_DATE);
        List<LocalDate> localDates = SamllDateUtil.getsAllDatesInTheDateRange(yyyyMMdd1, yyyyMMdd2);

        List<String> recommenderList = jsReportMapper.getAllRecommenders(start, end);

        List<String> dayList = localDates.stream().map(s -> s.format(DateTimeFormatter.ofPattern("yyyyMMdd"))).collect(Collectors.toList());

        List<List<Object>> allData = new ArrayList<>();
        List<Object> prod = new ArrayList<>();
        prod.add("dayCount");
        prod.addAll(dayList);
        allData.add(prod);

        recommenderList.stream().forEach(r -> {
            List<Object> recommend = new ArrayList<>();
            recommend.add(r);
            List<ReportVO> dataRecommender = jsReportMapper.getDataRecommender(start, end, r);
            Map<String, Integer> dataMap = dataRecommender.stream().collect(Collectors.toMap(ReportVO::getShouDateFormat, ReportVO::getCount));
            dayList.stream().forEach(d->{
                if (dataMap.get(d)!=null) {
                    recommend.add( String.valueOf(dataMap.get(d)));
                } else {
                    recommend.add(0);
                }
            });
            allData.add(recommend);
        });
        //allData.forEach(System.out::println);
        return allData;
    }
}
