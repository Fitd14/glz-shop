package com.cloud.shop.controller;

import com.cloud.smy.service.OrderBackService;
import com.glz.model.ResponseResult;
import com.glz.pojo.OrderBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderBack")
public class OrderBackController {
    @Autowired
    OrderBackService orderBackService;

    /**
     * 申请退货
     **/
    @PostMapping("/insert")
    public ResponseResult addOrderBac(@RequestBody OrderBack orderBack) {
        return orderBackService.addOrderBack(orderBack);
    }

    /**
     * 获得退货
     **/
    @GetMapping("/getAll")
    public ResponseResult all() {
        return orderBackService.allBack();
    }

    /**
     * 审核退货信息
     */
    @PostMapping("/udp")
    public ResponseResult updateOrderBack(@RequestBody OrderBack orderBack) {
        return orderBackService.updateOrderBack(orderBack);
    }

    /**
     * 取消 退货
     */
    @GetMapping("/del/{id}")
    public ResponseResult delOrderBack(@PathVariable("id") Long id) {
        return orderBackService.delOrderBack(id);
    }
}
