package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("upload_file")
public class UploadFile {
    @TableId
    /**
     * 上传文件id：UUID
     */
    private String id;
    /**
     * 文件名
     */
    private String name;
    /**
     * 是否为图片：0，图片。1，不是图片
     */
    private Integer isImg;
    /**
     * 文件后缀名
     */
    private String contentType;
    /**
     * 文件大小
     */
    private Integer size;
    /**
     * 文件物理上传路径
     */
    private String path;
    /**
     * 文件前端展示路径
     */
    private String url;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String updateTime;
}
