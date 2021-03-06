package com.cloud.smy.service;

import com.glz.model.ResponseResult;
import com.glz.pojo.Inventory;

public interface InventoryService {

    /**
     * 首次添加库存
     * @param inventory
     * @return
     */
    ResponseResult insert(Inventory inventory);

    /**
     * 更新消耗库存量
     * @param commodityId
     * @param count
     * @return
     */
    int updateCount(String commodityId,int count);

    /**
     * 更新库存总数
     * @param commodityId
     * @param totalCount
     * @return
     */
    ResponseResult updateTotalCount(String commodityId,int totalCount);

    /**
     * 删除库存
     * @param commodityId
     * @return
     */
    ResponseResult delete(String commodityId);
}
