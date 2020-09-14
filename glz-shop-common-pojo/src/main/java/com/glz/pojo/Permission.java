package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 后台菜单表
 */
@Data
@NoArgsConstructor
@TableName("t_permission")
public class Permission implements Serializable {

    @TableId
    private String id;

    //父节点id
    private long pid;

    //名称
    private String name;

    //权限值
    private String value;

    //图标
    private String icon;

    //类型 0为目录 1为菜单 2为按钮
    private String type;

    //前端资源路径
    private String uri;

    //状态 0为禁用 1为启用
    private String status;

    //创建时间
    private String createTime;

    //排序
    private String sort;

    @TableField(exist = false)
    private Set<Permission> permissions;
}
