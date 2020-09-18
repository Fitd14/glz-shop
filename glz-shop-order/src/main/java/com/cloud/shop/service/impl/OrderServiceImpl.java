package com.cloud.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.shop.mapper.OrderItemMapper;
import com.cloud.shop.mapper.OrderMapper;
import com.cloud.smy.service.OrderItemService;
import com.cloud.smy.service.OrderService;
import com.cloud.smy.service.UserShipAreaService;
import com.dsj.shop.service.CartService;
import com.glz.model.OrderDTO;
import com.glz.model.ResponseResult;
import com.glz.pojo.*;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Component
// @Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    UserShipAreaService userShipAreaService;
    @Reference
    CartService cartService;

    /**
     * 通过用戶ID查询该用户的所有订单
     */
    @Override
    public ResponseResult listOrder(String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        List<Order> orders = orderMapper.selectByMap(map);
        return new ResponseResult("200", "success", orders);
    }


    /**
     * 分页查询订单
     */
    @Override
    public ResponseResult listOrderPage(String userId, int pageSize, int pageNo) {
        Page<Order> pages = new Page<>(pageSize, pageNo);
        Page<Order> page = orderMapper.selectPage(pages, new QueryWrapper<Order>()
                .eq("user_id", userId));
        return new ResponseResult("200", "success", page.getRecords());
    }

    /**
     * 添加订单
     */
    @Override
    public ResponseResult addOrder(OrderDTO orderDTO) {
        System.out.println(orderDTO);
        List<Cart> carts = new ArrayList<>();
        BigDecimal totalPrice = new BigDecimal(0);
        Order order = new Order();
        UserShipArea userShipArea = null;
        int i = 0;
        if (orderDTO.getShipId() != null) {
            ResponseResult shipAreaById = userShipAreaService.getShipAreaById(orderDTO.getShipId());
            userShipArea = (UserShipArea) shipAreaById.getData();
        }
        if (orderDTO.getCids().length != 0) {
            for (Long cid : orderDTO.getCids()) {
                Cart cart = cartService.getCartById(cid);
                totalPrice = totalPrice.add(cart.getTotalPrice());
                carts.add(cart);
                System.out.println(totalPrice);
            }
        }
        if (userShipArea != null && carts.size() != 0) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            order.setOrderNo(uuid);
            order.setPayment(totalPrice);
            order.setUserId(orderDTO.getUserId());
            order.setShipName(userShipArea.getName());
            order.setPhone(userShipArea.getPhone());
            order.setPostCode(userShipArea.getPostCode());
            order.setProvince(userShipArea.getProvince());
            order.setCity(userShipArea.getCity());
            order.setRegion(userShipArea.getRegion());
            for (Cart cart2 : carts) {
                OrderItem item = new OrderItem();
                item.setOrderNo(uuid);
                item.setUserId(cart2.getUserId());
                item.setCommodityId(cart2.getCommodityId());
                item.setNumber(cart2.getCommodityCount());
                item.setPrice(cart2.getTotalPrice());
                item.setImg(cart2.getCommodityImg());
                item.setCommoditySubHead(cart2.getCommodityName());
                cartService.delCartById(cart2.getCartId());
                i = orderItemService.addOrderItem(item);
            }
            orderMapper.insert(order);
        }
        if (i > 0) {
            return ResponseResult.success();
        } else {
            return new ResponseResult("200", "fail", "请添加收货地址");
        }
    }


    /**
     * 根据ID删除订单
     */
    @Override
    public ResponseResult delOrder(String orderNo) {
        Map<String, Object> map = new HashMap<>();
        map.put("order_no", orderNo);
        int i = 0;
        int j = 0;
        i = orderMapper.deleteByMap(map);
        j = orderItemService.delOrderItem(orderNo);
        if (i != 0 && j != 0) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }

    }

    /**
     * 更新订单状态和支付时间
     */
    @Override
    public ResponseResult updateOrder(Order order) {
        int update = 0;
        update = orderMapper.update(order, new UpdateWrapper<Order>()
                .set("status", order.getStatus())
                .set("payment_time", new Date())
                .eq("order_no", order.getOrderNo()));

        if (update != 0) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }

    }

    /**
     * 根据用户id查询某一时间后的订单 没有实现
     */
    @Override
    public List<Order> timeOrders(String userId, String createTime) {
        List<Order> list = orderMapper.lists();
        return list;
    }

    /**
     * 更新订单 并不需要这个功能
     */
    @Override
    public ResponseResult updateStatus(Order order) {

        return ResponseResult.success();
    }

    /**
     * 根据ID集合删除订单
     */
    @Override
    public ResponseResult delByIdlist(List<String> ids) {
        int i = 0;
        for (String id : ids) {
            i = orderItemService.delOrderItem(id);
        }
        int j = orderMapper.deleteBatchIds(ids);
        if (i != 0 && j != 0) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    /**
     * 根据收货状态查询
     */
    @Override
    public ResponseResult getByStatus(String userId, int status) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        map.put("status", status);
        List<Order> orders = orderMapper.selectByMap(map);
        return new ResponseResult("200", "success", orders);
    }

    /**
     * 根据付款状态查询
     */
    @Override
    public ResponseResult getByPayStatus(String userId, int payStatus) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        map.put("payment_status", payStatus);
        List<Order> orders = orderMapper.selectByMap(map);
        return new ResponseResult("200", "success", orders);
    }

    @Override
    public ResponseResult allOrder() {
        List<Order> lists = orderMapper.lists();
        return new ResponseResult("200", "success", lists);
    }
}
