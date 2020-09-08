package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色菜单表
 */
@Data
@TableName("t_role_menu")
public class RoleMenu {

    @TableId
    //权限id
    private long roleId;

    //菜单id
    private long menuId;
    
}
