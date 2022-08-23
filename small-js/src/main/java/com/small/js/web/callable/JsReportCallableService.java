package com.small.js.web.callable;

import com.small.js.web.service.IJsReportService;
import com.small.js.web.vo.ReportData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/8/20 21:01
 * @version: v1.0
 */
@Service
public class JsReportCallableService {

    @Autowired
    private IJsReportService jsReportService;

    public Map<String, Object> getReports() throws Exception{
        // 三个线程的线程池，核心线程=最大线程，没有临时线程，阻塞队列无界
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        long start = System.currentTimeMillis();

        // 开启线程执行
        // 注意，此处Future对象接收线程执行结果不会阻塞，只有future.get()时候才会阻塞（直到线程执行完返回结果）
        Future future1 = executorService.submit(new SelectTask<>(jsReportService, "selectCurrentWeekList", new Object[]{}));

        Future future2 = executorService.submit(new SelectTask<>(jsReportService, "selectCurrentMonthList", new Object[]{}));

        Future future3 = executorService.submit(new SelectTask<>(jsReportService, "selectRecommenderLastDaysList", new Object[]{}));

        //此处用循环保证三个线程执行完毕，再去拼接三个结果
        /*do{
            System.out.println("多任务同时执行中...");
        }while (!(future1.isDone() && future2.isDone()));
*/
        Map<String, Object> result = new HashMap<>();
        result.put("weekData", future1.get());
        ReportData data = (ReportData) future2.get();
        result.put("monthData", data);
        List list = (List) future3.get();
        result.put("recommenders", list);
        long end = System.currentTimeMillis();
        System.out.println("并行执行：" + (end-start));

        return result;

    }



    //下面是三个真正执行任务（查数据库）的方法
    public List<Integer> selectCurrentWeekList(String cardId) throws Exception{
        List<Integer> result = jsReportService.selectCurrentWeekList();
        //Thread.sleep(1000);    //模拟添加1s耗时
        return result;
    }

    public ReportData selectCurrentMonthList(String cardId) throws Exception{
        ReportData result = jsReportService.selectCurrentMonthList();
        //Thread.sleep(1000);
        return result;
    }
    public List<List<Object>> selectRecommenderLastDaysList(String cardId) throws Exception{
        List<List<Object>> result = jsReportService.selectRecommenderLastDaysList();
        //Thread.sleep(1000);
        return result;
    }



    //任务线程类
    class SelectTask<T> implements Callable<T> {

        private Object object;
        private Object[] args;
        private String methodName;

        public SelectTask(Object object, String methodName, Object[] args) {
            this.object = object;
            this.args = args;
            this.methodName = methodName;
        }

        @Override
        public T call() throws Exception {
            Method method = object.getClass().getMethod(methodName);   //此处应用反射机制，String.class是根据实际方法参数设置的
            return (T) method.invoke(object);
        }
    }
}
