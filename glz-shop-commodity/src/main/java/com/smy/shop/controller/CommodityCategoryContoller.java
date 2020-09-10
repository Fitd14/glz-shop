package com.smy.shop.controller;

import com.glz.model.ResponseResult;
import com.glz.pojo.CommodityCategory;
import com.smy.shop.service.CommodityCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commodityCategory")
public class CommodityCategoryContoller {
    @Autowired
    CommodityCategoryService service;

    @RequestMapping("/add")
    public ResponseResult add(CommodityCategory commodityCategory){
        return service.add(commodityCategory);
    }

    @RequestMapping("/selAll")
    public ResponseResult selAll(){
        return service.selAll();
    }
}
