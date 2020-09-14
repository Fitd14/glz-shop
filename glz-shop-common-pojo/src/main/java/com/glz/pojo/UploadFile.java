package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("upload_file")
public class UploadFile {

    @TableId
    private String id;

    private String name;

    private boolean isImg;

    private String contentType;

    private int size;

    private String path;

    private String url;

    private String createTime;

    private String updateTime;

    private String productId;
}
