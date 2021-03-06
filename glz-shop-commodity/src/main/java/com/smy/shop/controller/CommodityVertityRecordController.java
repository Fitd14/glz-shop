package com.smy.shop.controller;

import com.glz.model.ResponseResult;
import com.glz.pojo.CommodityVertityRecord;
import com.smy.shop.service.CommodityVertityRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commodityVertityRecord")
public class CommodityVertityRecordController {
    @Autowired
    CommodityVertityRecordService service;

    @RequestMapping("/add")
    public ResponseResult add(@RequestBody CommodityVertityRecord vertityRecord){

        return service.add(vertityRecord);
    }

    @RequestMapping("/del")
    public ResponseResult del(String id){
        return service.del(id);
    }

}
