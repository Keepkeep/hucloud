package com.expand.hucloud.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.expand.hucloud.base.mapper.SysPlatformMapper;
import com.expand.hucloud.common.entity.SysPlatformEntity;
import com.expand.hucloud.common.requstparams.SysPlatformRequstParams;
import com.expand.hucloud.common.service.SysPlatformService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

/**
 * @author hdq
 * @time 2023/12/29 16:17
 */
@Service
public class SysPlatformServiceImpl extends ServiceImpl<SysPlatformMapper, SysPlatformEntity> implements SysPlatformService {

    public Page<SysPlatformEntity> pageList(SysPlatformRequstParams sysPlatformRequstParams){
        PageHelper.startPage(sysPlatformRequstParams.getPageNum(),sysPlatformRequstParams.getPageSize());
        return this.baseMapper.pageList(sysPlatformRequstParams);
    }

}
