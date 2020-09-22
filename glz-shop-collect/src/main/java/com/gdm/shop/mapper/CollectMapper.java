package com.gdm.shop.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glz.pojo.Collect;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CollectMapper extends BaseMapper<Collect> {
    /**
     * 分页查询根据收藏夹id查询收藏商品
     * @param typeId 收藏夹id
     * @param userId 用户id
     * @param page 当前页
     * @return 收藏商品的集合
     */
    @Select("select * from t_collect where type_id =#{typeId} and user_id=#{userId} order by create_time desc limit #{page},10;")
    List<Collect> getListIdPage(@Param("typeId") Long typeId,@Param("userId") Long userId,@Param("page") int page);

    /**
     * 根据收藏夹id查询收藏夹内所有的内容
     * @param typeId 收藏夹id
     * @param userId 用户id
     * @return 收藏夹的集合
     */
    @Select("select * from t_collect where type_id =#{typeId} and user_id=#{userId} order by create_time desc;")
    List<Collect> getListId(@Param("typeId") Long typeId,@Param("userId") Long userId);
    /**
     * 查询所有的收藏商品集合
     * @return 商品的集合
     */
    @Select("select * from t_collect where user_id=#{userId} order by create_time desc limit #{page},10;")
    List<Collect> getList(@Param("userId") String userId,@Param("page") int page);

    @Select("select * from t_collect where user_id=#{userId} order by create_time desc;")
    List<Collect> getListUid(@Param("userId") String userId);


}
