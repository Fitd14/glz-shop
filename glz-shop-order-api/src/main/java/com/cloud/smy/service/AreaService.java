package com.cloud.smy.service;

import com.glz.model.ResponseResult;

public interface AreaService {

    /**
     * 根据城市查找地区
     */
    ResponseResult allArea(int cityID);
}
