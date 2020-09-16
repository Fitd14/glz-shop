package com.cloud.smy.service;

import com.glz.model.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    String fileUpload(MultipartFile file);
}
