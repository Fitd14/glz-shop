package com.gdm.shop.controller;

import com.gdm.shop.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController

@RequestMapping("/file")
public class UploadController {
    @Autowired
    UploadService uploadService;
    @RequestMapping("/add")
    public String Upload(MultipartFile file){
        return uploadService.fileUpload(file);
    }
    @RequestMapping("/adds")
    public String Uploads(MultipartFile file){
        return this.uploadService.file(file);
    }
}
