package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_commodity_vertify_record")
public class CommodityVertityRecord {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long productId;
    private String createTime;
    private String vertityMan;
    private int status;
    private String detail;
}
