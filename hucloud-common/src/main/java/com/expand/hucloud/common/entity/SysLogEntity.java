package com.expand.hucloud.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author hdq
 * @time 2023/12/30 23:35
 */
@NoArgsConstructor
@TableName("sys_log")
@Accessors(chain = true)
@Data
@ApiModel(description = "系统日志")
public class SysLogEntity extends Model<SysLogEntity> {

    @TableId(type = IdType.ASSIGN_UUID, value = "id")
    @ApiModelProperty(value = "id主键")
    private Long id;

    @TableField("log_type")
    @ApiModelProperty(value = "日志类型 1 ：删除 2：添加 3：修改")
    private Integer logType;

    @TableField("log_module")
    @ApiModelProperty(value = "日志模块类型")
    private Integer logModule;

    @TableField("log_content")
    @ApiModelProperty(value = "日志内容")
    private String logContent;

    @TableField("log_time")
    @ApiModelProperty(value = "日志时间")
    private Date logTime;

    @TableField("log_ip")
    @ApiModelProperty(value = "日志ip")
    private String logIp;

    @TableField("log_user")
    @ApiModelProperty(value = "日志用户")
    private String logUser;


}
