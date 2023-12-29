package com.expand.hucloud.common.requstparams;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author hdq
 * @time 2023/12/29 16:49
 */

@Data
public class BaseRequestParams {

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("本次搜索中的页码")
    @JsonProperty(value = "PageNum")
    private Integer PageNum;

    @ApiModelProperty("本次搜索每页大小")
    @JsonProperty(value = "PageSize")
    private Integer PageSize;

    @ApiModelProperty("模糊查询字段")
    @JsonProperty(value = "keyword")
    private String keyword;

}
