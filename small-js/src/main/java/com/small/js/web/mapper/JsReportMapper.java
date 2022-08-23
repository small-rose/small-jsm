package com.small.js.web.mapper;

import com.small.js.web.vo.ReportVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsReportMapper {

    /**
     * 查当前周的日收文量
     * @param start
     * @param end
     * @return
     */
    List<ReportVO > selectCurrentWeekList(@Param( "start" )String start, @Param( "end" )String end);

    /**
     * 查推荐人
     * @param firstDay
     * @param last
     * @return
     */
    List<String> getAllRecommenders(@Param( "start" )String firstDay,@Param( "end" ) String last);


    List<ReportVO> getDataRecommender(@Param( "start" )String firstDay,@Param( "end" ) String last, @Param( "recommender" ) String recomender);
}
