package com.cloud.smy.service;

import com.glz.model.ResponseResult;
import com.glz.pojo.OrderBack;

public interface OrderBackService {
    /**
     * 审核商品退货状态
     */
    ResponseResult updateOrderBack(OrderBack orderBack);

    /**
     * 申请商品退货
     */
    ResponseResult addOrderBack(OrderBack orderBack);
    /**
     * 獲得需要退货的订单
     * */
    ResponseResult allBack();

    ResponseResult delOrderBack(Long id);

}
