package com.expand.hucloud.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.expand.hucloud.common.entity.SysPlatformEntity;
import com.expand.hucloud.common.requstparams.SysPlatformRequstParams;
import com.github.pagehelper.Page;

/**
 * @author hdq
 * @time 2023/12/29 16:01
 */

public interface SysPlatformService extends IService<SysPlatformEntity> {

    Page<SysPlatformEntity> pageList(SysPlatformRequstParams sysPlatformRequstParams);

}
