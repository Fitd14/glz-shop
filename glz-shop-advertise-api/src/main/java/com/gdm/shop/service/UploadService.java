package com.gdm.shop.service;

import com.glz.model.ResponseResult;
import com.glz.pojo.UploadFile;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String fileUpload(MultipartFile file);
    String file(MultipartFile file);
}
