package com.cloud.smy.controller;

import com.cloud.smy.service.InventoryService;
import com.glz.model.ResponseResult;
import com.glz.pojo.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @PostMapping("/save")
    public ResponseResult save(Inventory inventory){
        return inventoryService.insert(inventory);
    }

    @GetMapping("/update/count")
    public ResponseResult updateCount(Long commodityId,int count) {
        return inventoryService.updateCount(commodityId,count);
    }


}
