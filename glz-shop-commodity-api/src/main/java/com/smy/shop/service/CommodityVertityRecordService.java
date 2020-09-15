package com.smy.shop.service;

import com.glz.model.ResponseResult;
import com.glz.pojo.CommodityVertityRecord;

public interface CommodityVertityRecordService {
    ResponseResult add(CommodityVertityRecord vertityRecord);

    ResponseResult del(String id);
}
