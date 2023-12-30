package com.expand.hucloud.webs.controller;

import com.expand.hucloud.common.entity.SysPlatformEntity;
import com.expand.hucloud.common.enums.RestReturnCode;
import com.expand.hucloud.common.exception.AccessException;
import com.expand.hucloud.common.requstparams.SysPlatformRequstParams;
import com.expand.hucloud.common.response.AppResult;
import com.expand.hucloud.common.service.SysPlatformService;
import com.github.pagehelper.Page;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author hdq
 * @time 2023/12/29 16:33
 */

@RestController
@Slf4j
@RequestMapping("/sysPlatform")
public class SysPlatformController {

    @Autowired
    private SysPlatformService sysPlatformService;

    @ApiOperation("平台类型")
    @GetMapping("/pageList")
    public AppResult<Page<SysPlatformEntity>> pageList(@Valid SysPlatformRequstParams sysPlatformRequstParams){
        return AppResult.build(sysPlatformService.pageList(sysPlatformRequstParams));
    }

}
