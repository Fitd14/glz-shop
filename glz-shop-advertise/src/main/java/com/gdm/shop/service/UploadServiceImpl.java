package com.gdm.shop.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.injector.methods.Update;
import com.gdm.shop.mapper.UploadMapper;
import com.gdm.shop.utils.FtpUtil;
import com.glz.model.ResponseResult;
import com.glz.pojo.UploadFile;
import org.apache.commons.io.FileUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service

@Component
public class UploadServiceImpl implements UploadService {
    @Autowired
    UploadMapper uploadMapper;

    @Override
    public String fileUpload(MultipartFile file) {
        String uploadPath = "F://FTP";
        File uploadDirectory = new File(uploadPath);
        if (uploadDirectory.exists()) {
            if (!uploadDirectory.isDirectory()) {
                uploadDirectory.delete();
            }
        } else {
            uploadDirectory.mkdir();
        }
        if (file != null) {
            BufferedOutputStream bw = null;
            //获取文件名
            String fileName = file.getOriginalFilename();
            //文件的上传后的物理地址信息
            String url = uploadPath + "/" + UUID.randomUUID().toString() + getFileType(fileName);
            try {
                //判断是否有文件且是否为图片文件
                if (fileName != null && !"".equalsIgnoreCase(fileName.trim()) && isImageFile(fileName)) {
                    //创建输出文件对象
                    File outFile = new File(url);
                    //拷贝文件到输出文件对象
                    FileUtils.copyInputStreamToFile(file.getInputStream(), outFile);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bw != null) {
                        bw.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return url;
            // return new ResponseResult("200","success",url);
        } else {
            return "error";
        }
    }

    @Override
    public String file(MultipartFile file) {
        String FTP_HOST = "192.168.115.63:63";
        String pake = "lunbo";
        int FTP_PORT = 21;
        String FTP_USERNAME = "Administrator";
        String FTP_PASSWORD = "208016";
        String FTP_BASEPATH = "";
        try {
            //定义上传图片的目录结构
            SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
            String path = sdf.format(new Date());
            //设置新的文件名
            String newFileName = UUID.randomUUID().toString() +
                    file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            FtpUtil.uploadFile("192.168.115.63", 21,
                    "Administrator", "208016", "", pake+path,
                    newFileName, file.getInputStream());
            String imageURL = "http://" + FTP_HOST + "/" +pake+ path + newFileName;


            UploadFile uploadFile = new UploadFile();
            uploadFile.setId(UUID.randomUUID().toString());
            uploadFile.setName(newFileName);
            uploadFile.setImg(true);
            uploadFile.setContentType(getFileType(newFileName));
            uploadFile.setSize((int)(file.getSize()/1024));
            uploadFile.setPath(imageURL);
            uploadFile.setUrl(imageURL);
            uploadFile.setCreateTime(DateUtil.now());
            uploadMapper.insert(uploadFile);
            return imageURL;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    private Boolean isImageFile(String fileName) {
        String[] img_type = new String[]{".jpg", ".jpeg", ".png", ".gif", ".bmp"};
        if (fileName == null) {
            return false;
        }
        fileName = fileName.toLowerCase();
        for (String type : img_type) {
            if (fileName.endsWith(type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件后缀名
     *
     * @param fileName
     * @return
     */
    private String getFileType(String fileName) {
        if (fileName != null && fileName.indexOf(".") >= 0) {
            return fileName.substring(fileName.lastIndexOf("."), fileName.length());
        }
        return "";
    }

    /**
     * 将上传信息填入数据库
     */
    public int insertFile(MultipartFile file) {
        UploadFile uploadFile = new UploadFile();
        Integer a = 0;
        if (isImageFile(file.getOriginalFilename())) {
            a = 1;
        }
        uploadFile.setId(UUID.randomUUID().toString());
        uploadFile.setName(file.getOriginalFilename());
        uploadFile.setContentType(file.getContentType().toLowerCase());
        uploadFile.setImg(true);
        uploadFile.setPath(fileUpload(file));
        uploadFile.setSize(fileUpload(file).length());
        uploadFile.setUrl(fileUpload(file));
        return uploadMapper.insert(uploadFile);
    }
}
