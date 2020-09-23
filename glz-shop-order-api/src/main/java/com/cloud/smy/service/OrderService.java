package com.cloud.smy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.glz.model.DyOrder;
import com.glz.model.OrderDTO;
import com.glz.model.ResponseResult;
import com.glz.pojo.Cart;
import com.glz.pojo.Order;

import java.util.List;

public interface OrderService {
    /**
     * 添加订单
     */
    ResponseResult addOrder(OrderDTO orderDTO);

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
    ResponseResult listOrder(String userId);

    /**
     * 分页查询
     */
    ResponseResult listOrderPage(String userId, int pageNo, int pageSize);

    /**
     * 测试动态sql
     */
    List<Order> timeOrders(String userId, String createTime);

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
    ResponseResult getByStatus(String userId, int status);

    /**
     * 根据付款状态查询
     */
    ResponseResult getByPayStatus(String userId, int payStatus);

    /**
     * 查看所有订单
     */
    ResponseResult allOrder();

    /**
     * 动态查看订单
     */
    ResponseResult dySelOrder(DyOrder dyOrder);

    /**
     * 查看在一段时间区间的订单
     */
    ResponseResult bwtOrder(String startTime, String endTime);

    /**
     * 统计各省的销售概况
     */
    ResponseResult countOrder();
}
