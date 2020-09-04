package com.smy.shop.service;

import com.glz.model.ResponseResult;
import com.glz.pojo.Commodity;

public interface CommodityService {

    ResponseResult save(Commodity commodity);

    ResponseResult deleteById(Long id);

    ResponseResult updateAwayStatusById(Long id,Integer awayStatus);

    ResponseResult updateStatusById(Long id,Integer status);

    ResponseResult getOne(String name);

}
