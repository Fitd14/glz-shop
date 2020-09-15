package com.smy.shop.controller;

import com.glz.model.ResponseResult;
import com.glz.pojo.CommodityAttribute;
import com.smy.shop.service.ComodityAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commodityAttribute")
public class CommodityAttributeController {

    @Autowired
    ComodityAttributeService comodityAttributeService;

    @RequestMapping("/add")
    public ResponseResult add(CommodityAttribute commodityAttribute){
        return comodityAttributeService.add(commodityAttribute);
    }

    @RequestMapping("/update")
    public ResponseResult update(String inputList, Long id){
        return comodityAttributeService.update(inputList, id);
    }

    @RequestMapping("/del")
    public  ResponseResult del(Long id){
        return comodityAttributeService.del(id);
    }

    @RequestMapping("/sel")
    public  ResponseResult sel(Long id){
        return comodityAttributeService.sel(id);
    }
}
