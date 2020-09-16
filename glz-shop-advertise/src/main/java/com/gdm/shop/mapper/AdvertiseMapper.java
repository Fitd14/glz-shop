package com.gdm.shop.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glz.pojo.Advertise;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertiseMapper extends BaseMapper<Advertise> {
    @Select("select * from t_home_advertise where end_time > now() order by start_time limit 0,5")
    List<Advertise> getShowList();
    @Select("select * from t_home_advertise LIMIT #{page},#{rows};")
    List<Advertise> getAllList(@Param("page") Integer page,@Param("rows") Integer rows);
}
