package com.smy.shop.service;

import com.glz.model.ResponseResult;
import com.glz.pojo.CommodityAttribute;

public interface ComodityAttributeService {
    ResponseResult add(CommodityAttribute commodityAttribute);

    ResponseResult update(String inputList,Long id);

    ResponseResult sel(Long id);

    ResponseResult del(Long id);
}
