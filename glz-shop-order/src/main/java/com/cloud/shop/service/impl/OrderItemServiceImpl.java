package com.cloud.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.shop.mapper.OrderItemMapper;
import com.cloud.smy.service.OrderItemService;
import com.glz.model.ResponseResult;
import com.glz.pojo.Order;
import com.glz.pojo.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Transactional
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

    @Autowired
    OrderItemMapper orderItemMapper;

    /**
     * 添加订单明细
     */
    @Override
    public int addOrderItem(OrderItem orderItem) {
        int insert = orderItemMapper.insert(orderItem);
        return insert;
    }

    /**
     * 分页查询
     */
    @Override
    public ResponseResult selItemPage(String orderNo, int pageNo, int pageSize) {
        Page<OrderItem> page = new Page<>(pageNo, pageSize);
        Page<OrderItem> items = orderItemMapper.selectPage(page, new QueryWrapper<OrderItem>()
                .eq("order_no", orderNo));
        return new ResponseResult("200", "success", items.getRecords());
    }

    /**
     * 不分页查询
     */
    @Override
    public ResponseResult selItem(String orderNo) {
        Map<String, Object> map = new HashMap<>();
        map.put("order_no", orderNo);
        List<OrderItem> items = orderItemMapper.selectByMap(map);
        return new ResponseResult("200", "success", items);
    }

    /**
     * 根据ID删除订单明细
     */
    @Override
    public int delOrderItem(String orderNo) {
        Map<String, Object> map = new HashMap<>();
        map.put("order_no", orderNo);
        int i = orderItemMapper.deleteByMap(map);
        return i;
    }

    /**
     * 根据更新订单明细
     */

    @Override
    public ResponseResult updateOrderItem(OrderItem orderItem) {
        int i = -1;
        i = orderItemMapper.updateById(orderItem);
        if (i != -1) {
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }


    @Override
    public int updateItemStatus(Order order) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderNo", order.getOrderNo());
        List<OrderItem> items = orderItemMapper.selectByMap(map);
        int i = 0;
        for (OrderItem item : items) {
            i = orderItemMapper.update(item, new UpdateWrapper<OrderItem>()
                    .set("status", order.getStatus())
                    .eq("order_no", order.getOrderNo()));
        }

        return i;
    }

    /**
     * 根据订单号和商品号查询一条订单明细
     */
    @Override
    public ResponseResult getByOrderNoAndCid(String orderBo, String cid) {
        Map<String, Object> map = new HashMap<>();
        map.put("order_no", orderBo);
        map.put("commodity_id", cid);
        List<OrderItem> items = orderItemMapper.selectByMap(map);
        OrderItem item = new OrderItem();
        item = items.get(0);

        return new ResponseResult("200", "success", item);
    }

}
