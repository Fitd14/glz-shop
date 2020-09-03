package com.glz.pojo;

import lombok.Data;

@Data
public class User {

    /**
     * 主键
     */
    private long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private String created;

    /**
     * 更新时间
     */
    private String updated;



}
