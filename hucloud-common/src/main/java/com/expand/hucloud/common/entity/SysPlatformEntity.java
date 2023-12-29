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

/**
 * @Description 平台配置
 *
 */
@Data
@NoArgsConstructor
@TableName("sys_platform")
@ApiModel(description = "系统配置信息")
public class SysPlatformEntity extends Model<SysPlatformEntity> {

    @TableId(type = IdType.ASSIGN_UUID, value = "id")
    @ApiModelProperty(value = "id主键")
    private Long id;

    @ApiModelProperty(value = "平台名称")
    @TableField("plat_name")
    private String platName;

    @ApiModelProperty(value = "平台编码")
    @TableField("plat_flag")
    private String platFlag;

    @ApiModelProperty(value = "平台类型")
    @TableField("plat_type")
    private String platType;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private String createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private String updateTime;

    @ApiModelProperty(value = "创建人")
    @TableField("creator")
    private String creator;

}
