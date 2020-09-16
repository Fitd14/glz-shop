package com.cloud.shop.mapper;

import com.cloud.smy.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController("/file")
public class FileUploadController {
    @Autowired
    FileUploadService fileUploadService;

    @RequestMapping("/upload")
    public String upload(MultipartFile file) {
        return fileUploadService.fileUpload(file);
    }

}