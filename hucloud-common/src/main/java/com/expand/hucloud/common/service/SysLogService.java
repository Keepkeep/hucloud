package com.expand.hucloud.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.expand.hucloud.common.entity.SysLogEntity;
import com.expand.hucloud.common.entity.SysPlatformEntity;
import com.expand.hucloud.common.event.ExceptionLogEvent;

/**
 *
 * 系统日志记录
 * @author hdq
 * @time 2023/12/30 23:21
 */
public interface SysLogService extends IService<SysLogEntity> {

    void saveSysLog(SysLogEntity sysLogEntity);
}
