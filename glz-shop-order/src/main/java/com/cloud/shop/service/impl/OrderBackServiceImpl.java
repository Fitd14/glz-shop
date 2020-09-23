package com.cloud.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloud.shop.mapper.OrderBackMapper;
import com.cloud.shop.mapper.OrderItemMapper;
import com.cloud.shop.mapper.OrderMapper;
import com.cloud.smy.service.OrderBackService;
import com.cloud.smy.service.OrderItemService;
import com.glz.model.ResponseResult;
import com.glz.pojo.Commodity;
import com.glz.pojo.OrderBack;
import com.glz.pojo.OrderItem;
import com.smy.shop.service.CommodityService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Transactional
public class OrderBackServiceImpl implements OrderBackService {
    @Autowired
    OrderBackMapper orderBackMapper;
    @Autowired
    OrderItemMapper orderItemMapper;

    @Reference
    CommodityService commodityService;


    /**
     * 审核退货
     */
    @Override
    public ResponseResult updateOrderBack(OrderBack orderBack) {
        int i = -1;
        int j = -1;
        i = orderBackMapper.updateById(orderBack);
        Map<String, Object> map = new HashMap<>();
        map.put("order_no", orderBack.getOrderNo());
        map.put("commodity_id", orderBack.getCommodityId());
        List<OrderItem> items = orderItemMapper.selectByMap(map);
        for (OrderItem item : items) {
            item.setStatus(orderBack.getStatus());
            j = orderItemMapper.updateById(item);
        }

        if (i != -1 && j != -1) {
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    /**
     * 申请退货
     */
    @Override
    public ResponseResult addOrderBack(OrderBack orderBack) {
        int insert = -1;
        insert = orderBackMapper.insert(orderBack);
        if (insert != -1) {
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult allBack() {
        List<OrderBack> orderBacks = orderBackMapper.selectList(new QueryWrapper<>());
        if (orderBacks.size() > 0) {
            for (OrderBack ob : orderBacks) {
                ResponseResult<Commodity> responseResult = commodityService.selectOne(ob.getCommodityId());
                Commodity commodity = responseResult.getData();
                ob.setCommodityName(commodity.getCommoditySubHead());
            }
        }
        return new ResponseResult("200", "success", orderBacks);
    }

    @Override
    public ResponseResult delOrderBack(Long id) {
        int i = -1;
        i = orderBackMapper.deleteById(id);
        if (i != -1) {
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult selOrderBack(Long id) {
        OrderBack orderBack = orderBackMapper.selectById(id);
        return new ResponseResult("200", "success", orderBack);
    }
}
