package com.smy.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.glz.model.ResponseResult;
import com.glz.pojo.CommodityCategory;
import com.smy.shop.mapper.CommodityCategoryMapper;
import com.smy.shop.service.CommodityCategoryService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public ResponseResult update(CommodityCategory commodityCategory) {
        CommodityCategory category = commodityCategoryMapper.selectById(commodityCategory.getId());
        int update = commodityCategoryMapper.update(category, new UpdateWrapper<CommodityCategory>().eq("id", commodityCategory.getId()));
        if (update > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult del(Long id) {
        int delete = commodityCategoryMapper.deleteById(id);
        if (delete > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult selAll() {
        List<CommodityCategory> commodityCategories = commodityCategoryMapper.selectList(new QueryWrapper<CommodityCategory>());
        if (commodityCategories.size()>0){
            return new ResponseResult("200","success",commodityCategories);
        }
        return ResponseResult.error();
    }
}
