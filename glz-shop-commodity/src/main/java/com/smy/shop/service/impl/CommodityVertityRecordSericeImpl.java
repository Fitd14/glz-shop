package com.smy.shop.service.impl;

import cn.hutool.core.date.DateUtil;
import com.glz.model.ResponseResult;
import com.glz.pojo.CommodityVertityRecord;
import com.smy.shop.mapper.CommodityVertityRecordMapper;
import com.smy.shop.service.CommodityService;
import com.smy.shop.service.CommodityVertityRecordService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Service
@Component
public class CommodityVertityRecordSericeImpl implements CommodityVertityRecordService {

    @Autowired
    CommodityVertityRecordMapper mapper;
    @Autowired
    CommodityService commodityService;

    @Override
    @Transactional
    public ResponseResult add(CommodityVertityRecord vertityRecord) {
        vertityRecord.setCreateTime(DateUtil.now());
        int insert = mapper.insert(vertityRecord);
        System.out.println("vertityRecord = " + vertityRecord);
        commodityService.updateStatusById(vertityRecord.getProductId(),
                vertityRecord.getVertityName(),
                vertityRecord.getStatus());
        if (insert > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult del(String id) {
        int delete = mapper.deleteById(id);
        if (delete > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }
}
