package com.expand.hucloud.common.event;

import com.expand.hucloud.common.exception.AccessException;
import com.expand.hucloud.common.response.AppResult;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 日志
 * @author hdq
 * @time 2023/12/30 23:13
 */
public class ExceptionLogEvent extends ApplicationEvent {
    @Getter
    private AccessException accessException;

    @Getter
    private AppResult appResult;

    public ExceptionLogEvent(Object source, AccessException accessException, AppResult appResult) {
        super(source);
        this.accessException = accessException;
        this.appResult = appResult;
    }

}
