package com.cloud.shop.service.impl;

import cn.hutool.core.util.IdUtil;
import com.cloud.shop.utils.FtpUtil;
import com.cloud.smy.service.FileUploadService;
import com.glz.model.ResponseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
@Component
@Transactional
public class FileUploadServiceImpl implements FileUploadService {
    @Value("${FTP_HOST}")
    private String FTP_HOST;
    @Value("${FTP_POST}")
    private int FTP_POST;

    @Override
    public String fileUpload(MultipartFile file) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
            String path = sdf.format(new Date());
            System.out.println(file.getOriginalFilename());

            String newFileName
                    = UUID.randomUUID().toString().replaceAll("-", "")
                    + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            FtpUtil.uploadFile("192.168.115.63", 21, "anonymous", "", "/", path
                    , newFileName, file.getInputStream());
            String imgURL = "http://" + this.FTP_HOST + path + newFileName;
            return imgURL;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
