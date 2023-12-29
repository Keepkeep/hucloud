package com.expand.hucloud.common.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import com.expand.hucloud.common.enums.RestReturnCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 统一返回
 *
 * @param <T>
 * @author hdq
 */
@Data
@ApiModel("统一分页返回值")
public class AppResult<T> {

    @JSONField(name = "StatusCode")
    @JsonProperty(value = "StatusCode")
    protected Integer statusCode;

    @JSONField(name = "BusinessCode")
    @JsonProperty(value = "BusinessCode")
    protected String businessCode;

    @JSONField(name = "OpCode")
    @JsonProperty(value = "OpCode")
    protected Integer code;

    @JSONField(name = "OpDesc")
    @JsonProperty(value = "OpDesc")
    protected String opDesc;

    @JSONField(name = "PageNum")
    @JsonProperty(value = "PageNum")
    protected Integer pageNum;

    @JSONField(name = "PageSize")
    @JsonProperty(value = "PageSize")
    protected Integer pageSize;

    @JSONField(name = "Pages")
    @JsonProperty(value = "Pages")
    protected Integer pages;

    @JSONField(name = "Total")
    @JsonProperty(value = "Total")
    protected Long total;

    @JSONField(name = "Reserve")
    @JsonProperty(value = "Reserve")
    protected String reserve;

    @JSONField(name = "AccessToken")
    @JsonProperty(value = "AccessToken")
    protected String accessToken;

    @JSONField(name = "LocalTime")
    @JsonProperty(value = "LocalTime")
    protected String localTime;

    @JSONField(name = "Trace")
    @JsonProperty(value = "Trace")
    protected String trace;

    @JSONField(name = "STATUS")
    @JsonProperty(value = "STATUS")
    protected Integer status;

    @JSONField(name = "APPLICATION_ID")
    @JsonProperty(value = "APPLICATION_ID")
    protected String applicationId;

    @JSONField(name = "Data")
    @JsonProperty(value = "Data")
    protected T data;

    private static final String OMOF_STR = "omof";

    public AppResult() {
        this.total = 0L;
        this.statusCode = 200;
        this.code = RestReturnCode.OK.getCode();
        this.opDesc = RestReturnCode.OK.getMessage();

        this.localTime = LocalDateTime.now().toString();
        this.trace = OMOF_STR + "_" + System.currentTimeMillis();
        this.businessCode = RestReturnCode.BUSINESS_OK.getCode().toString();
        this.applicationId = OMOF_STR;
    }

    private AppResult(Integer code, String opDesc, T data) {
        this.total = 0L;
        this.code = code;
        this.opDesc = opDesc;
        this.data = data;

        this.statusCode = 200;
        this.localTime = LocalDateTime.now().toString();
        this.trace = OMOF_STR + "_" + System.currentTimeMillis();
        this.businessCode = RestReturnCode.BUSINESS_OK.getCode().toString();
        this.applicationId = OMOF_STR;

        if (data instanceof Page) {
            Page page = (Page) data;
            this.setTotal(page.getTotal());
            this.setPages(page.getPages());
            this.setPageSize(page.getPageSize());
            this.setPageNum(page.getPageNum());
        } else if (data instanceof PageInfo) {
            PageInfo page = (PageInfo) data;
            this.setTotal(page.getTotal());
            this.setPages(page.getPages());
            this.setPageSize(page.getPageSize());
            this.setPageNum(page.getPageNum());
        } else if (data instanceof List) {
            List list = (List) data;
            this.setTotal(Integer.toUnsignedLong(list.size()));
        }

    }

    public AppResult(T data) {
        this();
        this.data = data;
    }

    public static <T> AppResult<T> build() {
        return new AppResult();
    }

    public static <T> AppResult<T> build(IReturnCode returnCode) {
        return new AppResult(returnCode.getCode(), returnCode.getMessage(), (Object) null);
    }

    public static <T> AppResult<T> build(T data) {
        return new AppResult(RestReturnCode.OK.getCode(), RestReturnCode.OK.getMessage(), data);
    }

    public static <T> AppResult<T> build(IReturnCode returnCode, T data) {
        return new AppResult(returnCode.getCode(), returnCode.getMessage(), data);
    }

    public static <T> AppResult<T> build(T data, PageInfo page) {
        AppResult<T> appResult = build(data);
        appResult.setTotal(page.getTotal());
        appResult.setPages(page.getPages());
        appResult.setPageSize(page.getPageSize());
        appResult.setPageNum(page.getPageNum());
        return appResult;
    }

    public static <T> AppResult<T> build(T data, IPage page) {
        AppResult<T> appResult = build(data);
        appResult.setTotal(page.getTotal());
        appResult.setPages(Math.toIntExact(page.getPages()));
        appResult.setPageSize(Math.toIntExact(page.getSize()));
        appResult.setPageNum(Math.toIntExact(page.getCurrent()));
        return appResult;
    }
}
