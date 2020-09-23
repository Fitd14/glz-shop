package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("t_order")
public class Order {
    /**
     * 订单ID
     */
    @TableId
    String orderNo;
    /**
     * 用户ID
     */
    String UserId;
    /**
     * 总价
     */
    BigDecimal payment;
    /**
     * 发货状态 0：未发货 1：已发货
     */
    int status;

    /**
     * 支付状态 0:未支付；1：已支付；2申请退款；3：已退款
     */
    int paymentStatus;
    /**
     * 支付时间
     */
    String paymentTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT)
    String createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    String updateTime;
    /**
     * 收货人姓名
     * */
    String shipName;


    /**
     * 邮政编码
     * */
    String postCode;
    /**
     * 地址详情
     * */
    String detailAddress;
    /**
     * 手机号
     * */
    String phone;
    /**
     * 收货省份
     * */
    String province;
    /**
     * 收货城市
     * */
    String city;
    /**
     * 收货地区
     * */
    String region;

    /**
     * 描述
     * */
    String memo;
}
