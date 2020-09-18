package com.cloud.shop.controller;

import com.cloud.smy.service.OrderItemService;
import com.glz.model.ResponseResult;
import com.glz.pojo.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 不分页根据订单号查询
     */
    @GetMapping("/orderItem/list")
    public ResponseResult getList(@RequestParam("orderNo") String orderNo) {
        return orderItemService.selItem(orderNo);
    }

    /**
     * 根据更新订单明细
     */
    @PostMapping("/orderItem/udp")
    public ResponseResult updateOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemService.updateOrderItem(orderItem);
    }

    /**
     * 根据订单号和商品号查询一条订单明细
     */
    @GetMapping("/orderItem/getByOCid")
    public ResponseResult getByOrderNoAndCid(@RequestParam("orderNo") String orderBo, @RequestParam("cid") String cid) {
        return orderItemService.getByOrderNoAndCid(orderBo, cid);
    }

    /**
     * 通过ID查找数据
     */
    @GetMapping("/orderItem/sel/{id}")
    public ResponseResult getByid(@PathVariable("id") int id) {
        return orderItemService.getByOrderItemId(id);
    }

}
