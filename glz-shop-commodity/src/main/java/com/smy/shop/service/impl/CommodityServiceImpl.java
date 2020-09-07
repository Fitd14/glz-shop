package com.smy.shop.service.impl;



import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glz.enums.CommodityStatusEnum;
import com.glz.model.ResponseResult;
import com.glz.pojo.Commodity;
import com.smy.shop.mapper.CommodityMapper;
import com.smy.shop.service.CommodityService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityMapper commodityMapper;

    public String date(){
        String now = DateUtil.now();
        return now;
    }

    @Override
    public ResponseResult save(Commodity commodity) {

        commodity.setCreateTime(date());
        commodity.setUpdateTime(date());
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
    public ResponseResult updateAwayStatusById(Long id, Integer putawayStatus) {
        Commodity oldCommodity = commodityMapper.selectById(id);
        int row = commodityMapper.update(oldCommodity, new UpdateWrapper<Commodity>()
                .set("update_time",date())
                .set("putaway_status", putawayStatus)
                .eq("id", id));
        if(row > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult updateStatusById(Long id, Integer status) {
        Commodity oldCommodity = commodityMapper.selectById(id);
        int row = commodityMapper.update(oldCommodity, new UpdateWrapper<Commodity>()
                .set("update_time",date())
                .set("status", status)
                .eq("id", id));
        if (row >0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }



    @Override
    public ResponseResult getByOther(String commodityName, String commoditySubHead, String brand) {
        List<Commodity> commoditys = commodityMapper.getByOther(commodityName, commoditySubHead, brand);
        if (commoditys.size()>0){
            return new ResponseResult("200","success",commoditys);
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult getLimit( Long pageNo,Long pageSize,Integer putawayStatus) {
        IPage<Commodity> page = new Page<>(pageNo,pageSize);
        IPage<Commodity> result = commodityMapper.selectPage(page,new QueryWrapper<Commodity>().eq("putaway_status",putawayStatus));
        if (result.getRecords() != null){
            return new ResponseResult("200","success",result);
        }
        return ResponseResult.error();
    }


}
