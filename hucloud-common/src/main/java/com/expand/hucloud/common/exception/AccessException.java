package com.expand.hucloud.common.exception;


import com.expand.hucloud.common.enums.RestReturnCode;
import lombok.Data;

/**
 * @Description: 自定义异常类
 */
@Data
public class AccessException extends RuntimeException {

    private int code;
    private String message;

    /**
     * 错误构造类
     * @param errorCode
     */
    public AccessException(RestReturnCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    /**
     * 错误构造类
     *
     * @param code    错误码
     * @param message 错误信息
     */
    public AccessException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}
