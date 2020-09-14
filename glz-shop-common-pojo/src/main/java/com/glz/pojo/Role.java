package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色表
 */
@Data
@TableName("t_role")
public class Role implements Serializable {

    @TableId
    private String id;

    //角色code
    private String code;

    //角色名
    private String name;

    private String createTime;

    private String updateTime;

}
