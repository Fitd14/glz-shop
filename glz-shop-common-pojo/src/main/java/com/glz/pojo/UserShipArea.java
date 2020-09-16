package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_member_ship_area")
public class UserShipArea {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    Long id;
    /**
     * 用戶ID
     */
    String userId;
    /**
     * 收貨人姓名
     */
    String name;
    /**
     * 是否爲默认地址
     */
    int defaultStatus;
    /**
     * 邮政编码
     */
    String postCode;
    /**
     * 地址详情
     */
    String detailAddress;
    /**
     * 手机号
     */
    String phone;
    /**
     * 收货省份
     */
    String province;
    /**
     * 收货城市
     */
    String city;
    /**
     * 收货地区
     */
    String area;

    /**
     * 收货地区
     */
    String region;


}
