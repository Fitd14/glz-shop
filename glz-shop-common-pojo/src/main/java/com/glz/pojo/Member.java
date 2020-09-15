package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户表
 */
@Data
@TableName("t_member")
public class Member implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    //用户名
    private String username;

    //密码
    private String password;

    //昵称
    private String nickname;

    //手机号
    private String phone;

    //邮箱
    private String email;

    //状态 0为正常 1为禁用
    private int status;

    //头像
    private String icon;

    //性别
    private int gender;

    //生日
    private String birthday;

    private String createTime;

    private String updateTime;
}
