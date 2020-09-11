package com.smy.shop.service;

import com.glz.model.ResponseResult;
import com.glz.pojo.UploadFile;

public interface UploadService {
    ResponseResult upload(UploadFile uploadFile);
}
