package com.smy.shop.service.impl;

import cn.hutool.core.date.DateUtil;
import com.glz.model.ResponseResult;
import com.glz.pojo.UploadFile;
import com.smy.shop.mapper.UploadMapper;
import com.smy.shop.service.UploadService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Service
@Component
public class UploadServiceImpl implements UploadService {
    @Autowired
    UploadMapper uploadMapper;
    @Override
    public ResponseResult upload(UploadFile uploadFile) {
        uploadFile.setCreateTime(DateUtil.now());
        uploadFile.setUpdateTime(DateUtil.now());
        int insert = uploadMapper.insert(uploadFile);
        if (insert>0){
            return  ResponseResult.success();
        }
        return ResponseResult.error();
    }
}
