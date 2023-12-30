package com.expand.hucloud.common.event.Listener;

import com.expand.hucloud.common.entity.SysLogEntity;
import com.expand.hucloud.common.event.ExceptionLogEvent;
import com.expand.hucloud.common.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

/**
 *
 * 日志时间监听
 * @author hdq
 * @time 2023/12/30 23:19
 */

@Slf4j
@Configuration
public class ExceptionLogEventListener {

    @Autowired
    private SysLogService sysLogService;
    @EventListener
    public void saveLog(ExceptionLogEvent event) {
        //todo 日志对象构建
        SysLogEntity sysLogEntity = new SysLogEntity();

        // 保存日志
        sysLogService.saveSysLog(sysLogEntity);
        log.info("异常异常日志保存成功");
    }

}
