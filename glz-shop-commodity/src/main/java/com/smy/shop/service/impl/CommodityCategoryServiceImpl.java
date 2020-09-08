package com.smy.shop.service.impl;

import com.glz.model.ResponseResult;
import com.glz.pojo.CommodityCategory;
import com.smy.shop.mapper.CommodityCategoryMapper;
import com.smy.shop.service.CommodityCategoryService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Service
@Component
public class CommodityCategoryServiceImpl implements CommodityCategoryService {

    @Autowired
    CommodityCategoryMapper commodityCategoryMapper;
    @Override
    public ResponseResult add(CommodityCategory commodityCategory) {
        int insert = commodityCategoryMapper.insert(commodityCategory);
        if (insert > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }
}
