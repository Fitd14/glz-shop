package com.glz.model;

import lombok.Data;

/**
 * 作为添加订单的参数对象
 */
@Data
public class OrderDTO {
    /**
     * 用户ID
     */
    String userId;
    /**
     * 购物车订单集合
     */
    Long[] cids;
    /**
     * 收货地址ID
     */
    Long shipId;
    /**
     * 支付状态
     * */
    int paymentStatus;
    /**
     * 描述
     * */
    String memo;
}
