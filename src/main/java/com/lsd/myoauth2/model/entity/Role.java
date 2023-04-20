package com.lsd.myoauth2.model.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("role") // 表名为 role
public class Role {

    @TableId("id")
    private Integer id;

    @TableField(value = "description")
    private String description;

    @TableField(value = "created_time")
    private DateTime createdTime;

    @TableField(value = "name")
    private String name;

    @TableField(value = "role")
    private String role;

}
