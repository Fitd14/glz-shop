package com.smy.shop.controller;

import com.glz.model.ResponseResult;
import com.glz.pojo.Commodity;
import com.glz.pojo.CommodityAttribute;
import com.glz.pojo.CommodityCategory;
import com.glz.pojo.Inventory;
import com.smy.shop.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/commodity")
public class CommdityController {

    @Autowired
    CommodityService commodityService;
    @RequestMapping("/save")
    public ResponseResult save(@RequestBody Commodity commodity, Inventory inventory){
         return commodityService.save(commodity,  inventory);
    }

    @RequestMapping("/del")
    public ResponseResult del(String id){
         return commodityService.deleteById(id);
    }
    @RequestMapping("/away")
    public ResponseResult updateAwayStatus(String id, Integer putawayStatus){
        ResponseResult responseResult = commodityService.updateAwayStatusById(id, putawayStatus);
        return responseResult;
    }

    @RequestMapping("/check")
    public ResponseResult updateStatus(String id,Long uid,String detail){
        return commodityService.updateStatusById(id, uid,detail);
    }

    @RequestMapping("/sel")
    public ResponseResult sel(String commodityName, String commoditySubHead, String brand, String max, String min,String specificType, Long pageNo, Long pageSize, Integer putawayStatus){
        return   commodityService.getByOther(commodityName, commoditySubHead, brand,max,min,specificType,pageNo,pageSize,putawayStatus);
    }

    @RequestMapping("/limit")
    public ResponseResult limit(Long pageNo,Long pageSize,Integer putawayStatus){
        return commodityService.getLimit(pageNo, pageSize,putawayStatus);
    }

    @RequestMapping("/selectOne/{id}")
    public ResponseResult selOne(@PathVariable String id){
        return commodityService.selectOne(id);
    }

    @RequestMapping("/selectAll")
    public ResponseResult selectAll(){
        return commodityService.selectAll();
    }
}
