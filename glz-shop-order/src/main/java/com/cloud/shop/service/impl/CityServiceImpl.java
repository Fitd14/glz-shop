package com.cloud.shop.service.impl;

import com.cloud.shop.mapper.CityMapper;
import com.cloud.smy.service.CityService;
import com.glz.model.ResponseResult;
import com.glz.pojo.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Transactional
public class CityServiceImpl implements CityService {
    @Autowired
    CityMapper cityMapper;

    /**
     * 根据省份查找城市
     */
    @Override
    public ResponseResult allCity(int provinceID) {
        List<City> cities = new ArrayList<>();
        if (provinceID != 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("provinceID", provinceID);
            cities = cityMapper.selectByMap(map);
        }
        return new ResponseResult("200", "success", cities);
    }
}
