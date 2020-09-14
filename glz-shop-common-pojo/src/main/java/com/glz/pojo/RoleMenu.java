package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * 角色菜单表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_role_menu")
public class RoleMenu implements Serializable {

    @TableId
    //权限id
    private String roleId;

    //菜单id
    private String menuId;

    @TableField(exist = false)
    private Set<Permission> menuAll;

    @TableField(exist = false)
    private String[] menuIds;

    public RoleMenu(String roleId, String menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }
}
