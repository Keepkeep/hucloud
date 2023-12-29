package com.expand.hucloud.webs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.expand.hucloud"})
public class HucloudWebsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HucloudWebsApplication.class, args);
    }

}
