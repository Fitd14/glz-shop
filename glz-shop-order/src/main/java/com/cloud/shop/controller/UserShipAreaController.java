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
     * 添加/更新收货地址
     * 对象有ID时为更新 无ID时为添加
     */
    @PostMapping("/add")
    public ResponseResult addShipArea(@RequestBody UserShipArea userShipArea) {
        if (userShipArea.getId() != null) {
            return userShipAreaService.udpShipArea(userShipArea);
        }
        System.out.println(userShipArea);
        return userShipAreaService.addShipArea(userShipArea);
    }

    /**
     * 根据ID查找地址
     */
    @GetMapping("/area/id/{id}")
    public ResponseResult getAreaById(@PathVariable("id") Long id) {
        return userShipAreaService.getShipAreaById(id);
    }

    /**
     * 根据ID删除地址
     */
    @GetMapping("/area/del/{id}")
    public ResponseResult delAreaById(@PathVariable("id") Long id) {
        return userShipAreaService.delShipAreaById(id);
    }

}
