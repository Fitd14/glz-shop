package com.smy.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.glz.enums.CommodityStatusEnum;
import com.glz.model.ResponseResult;
import com.glz.pojo.Commodity;
import com.smy.shop.mapper.CommodityMapper;
import com.smy.shop.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.UpdatableSqlQuery;

public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public ResponseResult save(Commodity commodity) {
        commodity.setStatus(CommodityStatusEnum.CHECKING.getCode());
        int row = commodityMapper.insert(commodity);
        if(row > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult deleteById(Long id) {
        int row = commodityMapper.deleteById(id);
        if(row > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult updateAwayStatusById(Long id, Integer awayStatus) {
        Commodity oldCommodity = commodityMapper.selectById(id);
        int row = commodityMapper.update(oldCommodity, new UpdateWrapper<Commodity>()
                .set("awayStatus", awayStatus)
                .eq("id", id));
        if(row > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult updateStatusById(Long id, Integer status) {
        return null;
    }

    @Override
    public ResponseResult getOne(String name) {
        return null;
    }
}
