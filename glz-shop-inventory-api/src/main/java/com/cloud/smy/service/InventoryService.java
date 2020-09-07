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
    int updateCount(Long commodityId,int count);

    /**
     * 更新库存总数
     * @param commodityId
     * @param totalCount
     * @return
     */
    ResponseResult updateTotalCount(Long commodityId,int totalCount);

    /**
     * 删除库存
     * @param commodityId
     * @return
     */
    ResponseResult delete(Long commodityId);
}
