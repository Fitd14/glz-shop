package com.cloud.shop.controller;

import com.cloud.smy.service.ProvinceService;
import com.glz.model.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProvinceController {
    @Autowired
    ProvinceService provinceService;

    @GetMapping("/province/all")
    public ResponseResult allProvince() {
        return provinceService.allProvince();
    }

}
