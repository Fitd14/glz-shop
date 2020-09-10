package com.cloud.shop.controller;

import com.cloud.smy.service.OrderItemService;
import com.glz.model.ResponseResult;
import com.glz.pojo.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderItemController {

    @Autowired
    OrderItemService orderItemService;

    /**
     * 分頁查詢
     */
    @GetMapping("/orderItem/page")
    public ResponseResult pageList(@RequestParam("orderNo") String orderNo, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        return orderItemService.selItemPage(orderNo, pageNo, pageSize);
    }


    /**
     * 根据更新订单明细
     */
    public ResponseResult updateOrderItem(OrderItem orderItem) {
        int i = orderItemService.updateOrderItem(orderItem);
        if (i != 0) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }


}
