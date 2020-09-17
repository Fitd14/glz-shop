package com.cloud.shop.controller;

import com.cloud.smy.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/orderFile")
public class FileUploadController {
    @Autowired
    FileUploadService fileUploadService;

    @PostMapping("/upload")
    public String upload(MultipartFile file) {
        return fileUploadService.fileUpload(file);
    }

    @GetMapping("/hello")
    public String getHello(){
        return "hello,你好吗，我很好,iamfine";
    }
}
