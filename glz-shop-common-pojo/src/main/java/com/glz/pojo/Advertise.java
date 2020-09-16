package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_home_advertise")
public class Advertise {
    @TableId
    /**
     * 轮播图片id
     */
    private String id;
    /**
     * 轮播名字
     */
    private String name;
    /**
     * 轮播类型0，pc端，1，移动端
     */
    private Integer type;
    /**
     * 图片地址
     */
    private String pic;
    /**
     * 轮播开始时间
     */
    private String startTime;
    /**
     * 轮播结束时间
     */
    private String endTime;
    /**
     * 状态：0，下线。1，上线
     */
    private Integer status;
    /**
     * 点击量
     */
    private Integer clickCount;
    /**
     * 订单量
     */
    private Integer orderCount;
    /**
     * 连接地址
     */
    private String url;
    /**
     * 备注
     */
    private String note;
    /**
     * 轮播排序排序
     */
    private Integer sort;
}
