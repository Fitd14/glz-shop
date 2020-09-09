package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("province")
public class Province {
    /**
     * 省份ID
     * */
    @TableId(type = IdType.AUTO)
    int pid;
    /**
     * 省份编号
     * */
    @TableField("provinceID")
    int provinceID;
    /**
     * 省份
     * */
    String pname;
}
