package com.cloud.shop.controller;

import com.cloud.smy.service.CityService;
import com.glz.model.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {
    @Autowired
    CityService cityService;

    /**
     * 查找城市
     */
    @GetMapping("/city/{provinceID}")
    public ResponseResult allCity(@PathVariable("provinceID") int provinceID) {
        return cityService.allCity(provinceID);
    }
}
