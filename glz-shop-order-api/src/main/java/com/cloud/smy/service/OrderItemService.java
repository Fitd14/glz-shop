package com.cloud.smy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.glz.model.ResponseResult;
import com.glz.pojo.Order;
import com.glz.pojo.OrderItem;

public interface OrderItemService extends IService<OrderItem> {

    /**
     * 添加订单明细
     */
    int addOrderItem(OrderItem orderItem);

    /**
     * 分页查询
     */
    ResponseResult selItemPage(String orderNo, int pageNo, int pageSize);

    /**
     * 根据订单号批量删除订单详细
     */
    int delOrderItem(String orderNo);


    /**
     * 更新订单明细状态
     */
    int updateOrderItem(OrderItem OrderItem);


    /**
     * 更新订单详情的状态
     */
    int updateItemStatus(Order order);

}
