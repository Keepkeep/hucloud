package com.expand.hucloud.webs;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class,scanBasePackages = {"com.expand.hucloud"})
@EnableAsync
@MapperScan(" com.expand.hucloud.**.mapper")
public class HucloudWebsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HucloudWebsApplication.class, args);
    }

}
