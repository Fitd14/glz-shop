package com.smy.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.glz.model.ResponseResult;
import com.glz.pojo.CommodityAttribute;
import com.smy.shop.mapper.CommodityAttributeMapper;
import com.smy.shop.service.ComodityAttributeService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class CommodityAttributeServiceImpl implements ComodityAttributeService {
    @Autowired
    CommodityAttributeMapper commodityAttributeMapper;
    @Override
    public ResponseResult add(CommodityAttribute commodityAttribute) {
        int insert = commodityAttributeMapper.insert(commodityAttribute);
        if (insert >0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult update(String inputList, Long id) {
        CommodityAttribute commodityAttribute = commodityAttributeMapper.selectById(id);
        int update = commodityAttributeMapper.update(commodityAttribute, new UpdateWrapper<CommodityAttribute>()
                .set("input_list", inputList)
                .eq("id", id));
        if (update > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult sel(Long id) {
        CommodityAttribute commodityAttributes =
                commodityAttributeMapper.selectById(new QueryWrapper<CommodityAttribute>().eq("id",id));
        if (commodityAttributes != null){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }
}
