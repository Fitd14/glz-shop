package com.cloud.shop.service.impl;

import com.cloud.shop.mapper.ProvinceMapper;
import com.cloud.smy.service.ProvinceService;
import com.glz.model.ResponseResult;
import com.glz.pojo.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
@Transactional
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    ProvinceMapper provinceMapper;

    /**
     * 查找所有的省份
     */
    @Override
    public ResponseResult allProvince() {
        List<Province> provinces = provinceMapper.selectList(null);
        return new ResponseResult("200", "success", provinces);
    }
}
