package com.expand.hucloud.base.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.expand.hucloud.base.mapper.SysLogMapper;
import com.expand.hucloud.common.constant.AppConstant;
import com.expand.hucloud.common.entity.SysLogEntity;
import com.expand.hucloud.common.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author hdq
 * @time 2023/12/31 00:00
 * @description 系统日志
 */
@Slf4j
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLogEntity> implements SysLogService {

    /**
     * 添加日志
     * @param sysLogEntity
     */
    @Async(AppConstant.DEFAULT_TASK)
    @Override
    public void saveSysLog(SysLogEntity sysLogEntity) {
        // todo 插入ES 或者clickhouse

        //插入mysql 数据库
        this.save(sysLogEntity);
        log.info("添加系统成功");
    }
}
