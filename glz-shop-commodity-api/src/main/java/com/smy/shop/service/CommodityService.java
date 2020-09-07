package com.smy.shop.service;


import com.glz.model.ResponseResult;
import com.glz.pojo.Commodity;
import org.apache.ibatis.annotations.Param;

public interface CommodityService {

    ResponseResult save(Commodity commodity);

    ResponseResult deleteById(Long id);

    ResponseResult updateAwayStatusById(Long id,Integer putawayStatus);

    ResponseResult updateStatusById(Long id,Integer status);

    ResponseResult getByOther(@Param("commodityName") String commodityName, @Param("commoditySubHead") String commoditySubHead, @Param("brand") String brand);

    ResponseResult getLimit(Long pageSize,Long pageNo,Integer putawayStatus);
}
