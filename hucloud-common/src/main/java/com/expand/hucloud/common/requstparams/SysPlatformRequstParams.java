package com.expand.hucloud.common.requstparams;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

/**
 * @author hdq
 * @time 2023/12/29 16:56
 */
@Data
public class SysPlatformRequstParams extends BaseRequestParams{

    @Null(message = "平台类型不能为空")
    @ApiModelProperty(value = "平台类型")
    private String platformName;

    @ApiModelProperty(value = "平台类型")
    private String platformType;
}
