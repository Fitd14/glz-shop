package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色表
 */
@Data
@TableName("t_role_user")
public class RoleUser implements Serializable {

    //用户id
    @TableId
    private String userId;

    //角色Id
    private String roleId;

}
