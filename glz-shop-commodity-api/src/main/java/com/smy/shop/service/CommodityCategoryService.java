package com.smy.shop.service;

import com.glz.model.ResponseResult;
import com.glz.pojo.CommodityCategory;

public interface CommodityCategoryService {
    ResponseResult add(CommodityCategory commodityCategory);

    ResponseResult update(CommodityCategory commodityCategory);

    ResponseResult del(Long id);

    ResponseResult selAll();


}
