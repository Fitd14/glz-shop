package com.cloud.shop.controller;

import com.cloud.smy.service.UserShipAreaService;
import com.glz.model.ResponseResult;
import com.glz.pojo.UserShipArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ship")
public class UserShipAreaController {

    @Autowired
    UserShipAreaService userShipAreaService;

    /**
     * 根据用户ID查找地址列表
     */
    @GetMapping("/area/{userId}")
    public ResponseResult getArea(@PathVariable("userId") String userId) {
        return userShipAreaService.allShipArea(userId);
    }
    /**
     * 添加收货地址
     * */
    @PostMapping("/add")
    public ResponseResult addShipArea(UserShipArea userShipArea) {
        return userShipAreaService.addShipArea(userShipArea);
    }

    /**
     * 根据ID查找地址
     */
    @GetMapping("/area/id/{id}")
    public ResponseResult getAreaById(@PathVariable("id") String id) {
        return userShipAreaService.getShipAreaById(id);
    }

    /**
     * 根据ID删除地址
     */
    @GetMapping("/area/del/{id}")
    public ResponseResult delAreaById(@PathVariable("id") String id) {
        return userShipAreaService.delShipAreaById(id);
    }

}
