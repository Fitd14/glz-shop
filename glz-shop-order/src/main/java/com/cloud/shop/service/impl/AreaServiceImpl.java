package com.cloud.shop.service.impl;

import com.cloud.shop.mapper.AreaMapper;
import com.cloud.smy.service.AreaService;
import com.glz.model.ResponseResult;
import com.glz.pojo.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class AreaServiceImpl implements AreaService {
    @Autowired
    AreaMapper areaMapper;

    /**
     * 根据城市查找地区
     */
    @Override
    public ResponseResult allArea(int cityID) {
        List<Area> areas = new ArrayList<>();
        if (cityID != 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("cityID", cityID);
            areas = areaMapper.selectByMap(map);
        }
        return new ResponseResult("200", "success", areas);
    }
}
