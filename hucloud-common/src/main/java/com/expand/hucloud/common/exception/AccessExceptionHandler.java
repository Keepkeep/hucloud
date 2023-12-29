package com.expand.hucloud.common.exception;


import com.expand.hucloud.common.enums.RestReturnCode;
import com.expand.hucloud.common.response.AppResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    @ExceptionHandler(value = AccessException.class)
    public AppResult handleOperationException(AccessException e, HttpServletResponse resp, HttpServletRequest req) {
        log.error("业务处理失败: {}", e.getMessage(),e);
        AppResult appResult = new AppResult();
        appResult.setStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        appResult.setBusinessCode(String.valueOf(e.getCode()));
        appResult.setOpDesc(e.getMessage());
        appResult.setCode(1);
        return appResult;
    }

    @ExceptionHandler(value = Exception.class)
    public AppResult handleOperationException(Exception e, HttpServletResponse resp, HttpServletRequest req) {
        log.error("业务处理失败: {}", e.getMessage(),e);
        AppResult appResult = new AppResult();
        appResult.setStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        appResult.setBusinessCode(String.valueOf(RestReturnCode.OTHRE.getCode()));
        appResult.setOpDesc(e.getMessage());
        return appResult;
    }


}
