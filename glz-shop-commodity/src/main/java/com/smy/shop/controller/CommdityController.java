package com.smy.shop.controller;

import com.glz.model.ResponseResult;
import com.glz.pojo.Commodity;
import com.glz.pojo.CommodityAttribute;
import com.glz.pojo.CommodityCategory;
import com.smy.shop.service.CommodityService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commodity")
public class CommdityController {

    @Autowired
    CommodityService commodityService;
    @RequestMapping("/save")
    public ResponseResult save(Commodity commodity, CommodityAttribute commodityAttribute, CommodityCategory commodityCategory){
         return commodityService.save(commodity, commodityAttribute, commodityCategory);
    }

    @RequestMapping("/del")
    public ResponseResult del(Long id){
         return commodityService.deleteById(id);
    }
    @RequestMapping("/away")
    public ResponseResult updateAwayStatus(Long id, Integer putawayStatus){
        ResponseResult responseResult = commodityService.updateAwayStatusById(id, putawayStatus);
        return responseResult;
    }

    @RequestMapping("/check")
    public ResponseResult updateStatus(Long id, Integer status){
        return commodityService.updateStatusById(id, status);
    }

    @RequestMapping("/sel")
    public ResponseResult sel(String commodityName, String commoditySubHead, String brand, String max, String min){
        return   commodityService.getByOther(commodityName, commoditySubHead, brand,max,min);
    }

    @RequestMapping("/limit")
    public ResponseResult limit(Long pageNo,Long pageSize,Integer putawayStatus){
        return commodityService.getLimit(pageNo, pageSize,putawayStatus);
    }

    @RequestMapping("/selectOne")
    public ResponseResult selOne(Long id){
        return commodityService.selectOne(id);
    }
}
