package com.cloud.smy.service;

import com.glz.model.ResponseResult;
import com.glz.pojo.City;

import java.util.List;

public interface CityService {
    /**
     * 根据省份查找所有城市
     */
    ResponseResult allCity(int provinceID);
}
