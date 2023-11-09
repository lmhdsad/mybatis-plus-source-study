package com.lmh.mp.tenant.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("factory")
public class Factory {

    @TableId("id")
    private Long id;

    @TableField("factory_name")
    private String factoryName;

    @TableField("user_info")
    private String userInfo;

    @TableField("tenant_id")
    private String tenantId;
}
