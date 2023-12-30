package com.expand.hucloud.common.exception;


import com.expand.hucloud.common.enums.RestReturnCode;
import com.expand.hucloud.common.event.ExceptionLogEvent;
import com.expand.hucloud.common.response.AppResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 统一异常处理
 *
 */
//@ControllerAdvice(basePackages = {"com.expand"})
@RestControllerAdvice(basePackages = {"com.expand"})
@Slf4j
public class AccessExceptionHandler {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @ExceptionHandler(value = AccessException.class)
    @ResponseStatus(HttpStatus.OK)
    public AppResult handleOperationException(AccessException e, HttpServletResponse resp, HttpServletRequest req) {
        log.error("业务处理失败: {}", e.getMessage(),e);
        AppResult appResult = new AppResult();
        appResult.setStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        appResult.setBusinessCode(String.valueOf(e.getCode()));
        appResult.setOpDesc(e.getMessage());
        appResult.setCode(1);

        //异步日志
        applicationEventPublisher.publishEvent(new ExceptionLogEvent(this,e,appResult));

        return appResult;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public AppResult handleOperationException(Exception e, HttpServletResponse resp, HttpServletRequest req) {
        log.error("业务处理失败: {}", e.getMessage(),e);
        AppResult appResult = new AppResult();
        appResult.setStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        appResult.setBusinessCode(String.valueOf(RestReturnCode.OTHRE.getCode()));
        appResult.setOpDesc(e.getMessage());
        return appResult;
    }


    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    public AppResult handleBindException(IllegalArgumentException e) {
        log.error("参数校验异常:", e);
        AppResult appResult = new AppResult();
        appResult.setStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        appResult.setStatusCode(RestReturnCode.FIELD_VALIDATE_ERROR.getCode());
        appResult.setOpDesc(e.getMessage());
        return appResult;
    }


    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.OK)
    public AppResult handleBindException(BindException e) {
        log.error("spring校验异常:", e);
        AppResult appResult = new AppResult();
        appResult.setStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        appResult.setOpDesc(RestReturnCode.FIELD_VALIDATE_ERROR.getMessage() +e.getAllErrors().get(0).getDefaultMessage());
        return appResult;
    }

}
