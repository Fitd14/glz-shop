package com.smy.shop.service.impl;

import com.glz.model.ResponseResult;
import com.glz.pojo.UploadFile;
import com.smy.shop.mapper.UploadMapper;
import com.smy.shop.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;

public class UploadServiceImpl implements UploadService {
    @Autowired
    UploadMapper uploadMapper;
    @Override
    public ResponseResult upload(UploadFile uploadFile) {
        int insert = uploadMapper.insert(uploadFile);
        if (insert>0){
            return  ResponseResult.success();
        }
        return ResponseResult.error();
    }
}
