package com.cloud.smy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cloud.smy.mapper.InventoryMapper;
import com.cloud.smy.service.InventoryService;
import com.glz.model.ResponseResult;
import com.glz.pojo.Inventory;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    public ResponseResult insert(Inventory inventory) {

        inventory.setExistingCount(inventory.getTotalCount());
        int row = inventoryMapper.insert(inventory);
        if(row > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public int updateCount(Long commodityId, int count) {
        Inventory oldInventory = inventoryMapper.selectOne(new QueryWrapper<Inventory>()
                .eq("commodity_id", commodityId));
        oldInventory.setExistingCount(oldInventory.getExistingCount()-count);
        oldInventory.setConsumeCount(oldInventory.getConsumeCount()+count);
        return inventoryMapper.update(oldInventory, new UpdateWrapper<Inventory>()
                .set("existing_count", oldInventory.getExistingCount())
                .set("consume_count", oldInventory.getConsumeCount())
                .eq("commodity_id", commodityId));
    }

    @Override
    public ResponseResult updateTotalCount(Long commodityId, int totalCount) {
        Inventory oldInventory = inventoryMapper.selectOne(new QueryWrapper<Inventory>()
                .eq("commodity_id", commodityId));
        oldInventory.setTotalCount(oldInventory.getExistingCount()+totalCount);
        oldInventory.setExistingCount(oldInventory.getExistingCount()+totalCount);
        int update = inventoryMapper.update(oldInventory, new UpdateWrapper<Inventory>()
                .set("total_count", totalCount)
                .set("existing_count", oldInventory.getExistingCount())
                .eq("commodity_id", commodityId));
        int result = inventoryMapper.update(oldInventory, new UpdateWrapper<Inventory>()
                .set("existing_count", oldInventory.getExistingCount())
                .set("consume_count", oldInventory.getConsumeCount())
                .eq("commodity_id", commodityId));
        if(result > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult delete(Long commodityId) {
        HashMap<String, Object> condition = new HashMap<>();
        condition.put("commodity_id",commodityId);
        int result = inventoryMapper.deleteByMap(condition);
        if(result > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }
}
