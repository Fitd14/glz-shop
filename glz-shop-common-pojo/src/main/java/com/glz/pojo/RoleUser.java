package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户角色表
 */
@Data
@TableName("t_role_user")
public class RoleUser {

    @TableId
    //用户id
    private long userId;

    //角色Id
    private long roleId;

}
