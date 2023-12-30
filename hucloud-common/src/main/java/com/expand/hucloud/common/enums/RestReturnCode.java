package com.expand.hucloud.common.enums;


import com.expand.hucloud.common.response.IReturnCode;

/**
 * Rest 返回枚举
 * @author hdq
 */
public enum RestReturnCode implements IReturnCode {

    OK(0, "请求成功"),
    OTHRE(1, "其他未知错误"),
    BUSY(2, "系统繁忙"),
    OPR_INVALID(4, "无效操作"),
    JSON_FORMAT_INVALID(7, "JSON格式无效"),
    JSON_CONTENT_INVALID(8, "JSON内容无效"),

    /**用户名或密码错误**/
    USER_ERROR(40001, "用户名或密码错误"),
    /**凭据超时或尚未获取凭证**/
    TOKEN_OVERTIME(40002, "凭据超时或尚未获取凭证，请重新注册"),
    /**请求参数不合法**/
    ILLEGAL_PARAM(40003, "请求参数不合法"),
    /**用户账号过期**/
    USER_EXPIRED(40004, "用户账号过期"),
    /**请求数据条目超过500条**/
    NUM_EXCEED(41001, "请求数据条目超过500条"),
    /**没有相关权限**/
    PERMISSION_FORBIDDEN(41005, "没有相关权限"),
    BUSINESS_OK(10000, "业务请求成功"),

    /**
     * 格式错误!
     */
    FIELD_VALIDATE_ERROR(10002, "格式错误!"),
    ;

    private Integer code;
    private String message;

    RestReturnCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
