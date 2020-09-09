package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("area")
public class Area {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    int aid;
    /**
     * 地区编号
     */
    @TableField("areaID")
    int areaID;
    /**
     * 地区
     */
    String area;
    /**
     * 所属城市ID
     */
    @TableField("cityID")
    int cityID;
}
