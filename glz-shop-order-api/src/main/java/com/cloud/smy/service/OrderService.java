package com.cloud.smy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.glz.model.ResponseResult;
import com.glz.pojo.Cart;
import com.glz.pojo.Order;

import java.util.List;

public interface OrderService extends IService<Order> {
    /**
     * 添加订单
     */
    ResponseResult addOrder(Cart cart, Long id);

    /**
     * 根据订单号删除订单
     */
    ResponseResult delOrder(String orderNo);

    /**
     * 更新order
     */
    ResponseResult updateOrder(Order order);

    /**
     * 查詢所有订单
     */
    ResponseResult listOrder(Long userId);

    /**
     * 分页查询
     */
    ResponseResult listOrderPage(Long userId, int pageNo, int pageSize);

    /**
     * 测试动态sql
     */
    List<Order> timeOrders(Long userId, String createTime);

    /**
     * 更新订单状态
     */
    ResponseResult updateStatus(Order order);

    /**
     * 根据ID批量删除
     */
    ResponseResult delByIdlist(List<String> ids);

    /**
     * 根据收货状态查询
     */
    ResponseResult getByStatus(int userId, int status);

    /**
     * 根据付款状态查询
     */
    ResponseResult getByPayStatus(int userId, int payStatus);
}
