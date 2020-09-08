package com.smy.shop.service.impl;

import com.glz.model.ResponseResult;
import com.glz.pojo.CommodityVertityRecord;
import com.smy.shop.mapper.CommodityVertityRecordMapper;
import com.smy.shop.service.CommodityVertityRecordService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Service
@Component
public class CommodityVertityRecordSericeImpl implements CommodityVertityRecordService {

    @Autowired
    CommodityVertityRecordMapper mapper;

    @Override
    public ResponseResult add(CommodityVertityRecord vertityRecord) {
        int insert = mapper.insert(vertityRecord);
        if (insert > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }
}
