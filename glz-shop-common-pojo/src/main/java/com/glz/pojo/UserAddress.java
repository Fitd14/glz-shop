package com.glz.pojo;

import lombok.Data;

@Data
public class UserAddress {

    /**
     * 主键
     */
    private long id;

    /**
     * 用户主键
     */
    private long userId;

    /**
     * 用户地址
     */
    private String userAddress;

    /**
     * 是否是默认地址
     */
    private Integer isDefault;

    /**
     * 创建时间
     */
    private String created;

    /**
     * 更新时间
     */
    private String updated;
}
