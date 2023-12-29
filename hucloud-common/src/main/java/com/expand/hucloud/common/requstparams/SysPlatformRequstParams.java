package com.expand.hucloud.common.requstparams;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hdq
 * @time 2023/12/29 16:56
 */
@Data
public class SysPlatformRequstParams extends BaseRequestParams{

    @ApiModelProperty(value = "平台类型")
    private String platformName;

    @ApiModelProperty(value = "平台类型")
    private String platformType;
}
