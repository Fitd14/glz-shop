package com.gdm.service;

import com.glz.pojo.CollectType;

import java.util.List;

public interface CollectTypeService {
    /**
     * 创建收藏夹类别
     * @param userId 用户id
     * @param typeName 类别名
     * @return 1,创建成功。2，创建失败
     */
    int createType(Long userId,String typeName);

    /**
     * 根据id查询收藏夹属性
     * @param typeId 类别id
     * @return 收藏夹类型对象
     */
    CollectType getId(Long typeId);

    /**
     * 获取所有的收藏夹
     * @return 返回收藏夹集合
     */
    List<CollectType> getList(Long userId);

    /**
     * 删除收藏夹先判断收藏夹是否有收藏商品，有收藏商品无法删除
     * @param typeId 收藏夹id
     * @param userId 用户id
     * @return 1,删除收藏夹。0，为删除
     */
    int delete(Long typeId,Long userId);

}
