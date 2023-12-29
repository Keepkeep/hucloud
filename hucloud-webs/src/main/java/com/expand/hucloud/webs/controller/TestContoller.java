package com.expand.hucloud.webs.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hdq
 * @time 2023/12/28 22:41
 */
@RestController
@Slf4j
@RequestMapping("/test/")
public class TestContoller {

    @ApiOperation("测试")
    @GetMapping("/test")
    public Object  test(){
        log.info("test");
        return "test";
    }
}
