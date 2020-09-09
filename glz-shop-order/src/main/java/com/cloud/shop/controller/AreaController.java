package com.cloud.shop.controller;

import com.cloud.smy.service.AreaService;
import com.glz.model.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AreaController {
    @Autowired
    AreaService areaService;

    /**
     * 查找地区
     */
    @GetMapping("/area/{cityID}")
    public ResponseResult allArea(@PathVariable("cityID") int cityID) {
        return areaService.allArea(cityID);
    }
}
