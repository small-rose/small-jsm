package com.small;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author small
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class SmallApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(SmallApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  SMALL资源管理平台启动成功   ლ(´ڡ`ლ)ﾞ  ");
    }
}
