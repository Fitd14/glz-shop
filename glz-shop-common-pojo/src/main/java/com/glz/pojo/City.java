package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("city")
public class City {
    /**
     * 城市ID
     * */
    @TableId(type = IdType.AUTO)
    int cid;
    /**
     * 城市编号
     * */
    @TableField("cityID")
    int cityID;
    /**
     * 城市名
     * */
    String city;
    /**
     *城市所属省份
     * */
    @TableField("provinceID")
    int provinceID;
}
