package com.small.js.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/7/31 9:07
 * @version: v1.0
 */
@Configuration
public class IdWorkerConfig {

    private long workId = 1;
    private long datacenterId = 1;
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(workId, datacenterId);
    }
}
