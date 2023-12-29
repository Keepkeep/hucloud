package com.expand.hucloud.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.expand.hucloud.common.entity.SysPlatformEntity;
import com.expand.hucloud.common.requstparams.SysPlatformRequstParams;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author hdq
 * @time 2023/12/29 16:09
 */
@Mapper
public interface SysPlatformMapper extends BaseMapper<SysPlatformEntity> {

    Page<SysPlatformEntity> pageList(@Param("sysPlatformRequstParams") SysPlatformRequstParams sysPlatformRequstParams);


}
