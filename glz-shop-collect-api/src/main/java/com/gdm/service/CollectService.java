package com.gdm.service;


import com.glz.pojo.Collect;
import com.glz.pojo.Commodity;

import java.util.List;

public interface CollectService {
    /**
     * 添加收藏
     * @param typeId 收藏夹id
     * @param userId 用户id
     * @return 1，收藏成功 0，收藏失败
     */
    int create(Long typeId,Long userId);

    /**
     * 根据收藏id查询收藏内容
     * @param cid 收藏id
     * @return 收藏对象
     */
    Collect getId(Long cid);

    /**
     * 删除收藏内容
     * @param cid 收藏id
     * @return 1，删除成功
     */
    int delete(Long cid);

    /**
     * 根据用户查询收藏商品集合分页
     * @param typeId 收藏夹id
     * @return 返回一定的分页数量集合
     */
    List<Collect> getListIdPage(Long typeId,Long userId,int page);

    /**
     * 根据收藏夹Id查询所有的收藏商品
     * @param typeId  收藏夹id
     * @param userId 用户id
     * @return 返回收藏夹集合
     */
    List<Collect> getListId(Long typeId,Long userId);

    /**
     * 根据用户查询所有收藏内容，分页
     * @param userId 用户id
     * @param page 当前页
     * @return  收藏商品集合分页
     */
    List<Collect> getList(Long userId,int page);

    /**
     * 删除多态消息
     * @param ids 删除收藏商品id集合
     */
    void deletes(List<Long> ids);
}
