package com.smy.shop.service.impl;



import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.smy.service.InventoryService;
import com.glz.enums.CommodityStatusEnum;
import com.glz.model.ResponseResult;
import com.glz.pojo.*;
import com.google.gson.internal.$Gson$Preconditions;
import com.smy.shop.mapper.CommodityMapper;
import com.smy.shop.service.*;
import org.apache.commons.io.FileUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Component
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityMapper commodityMapper;
    @Autowired
    ComodityAttributeService comodityAttributeService;
    @Autowired
    CommodityCategoryService commodityCategoryService;
    @Autowired
    CommodityVertityRecordService commodityVertityRecordService;
    @Value("localtion")
    String localtion;
    @Reference
    InventoryService inventoryService;
    @Autowired
    UploadService uploadService;

    public String date(){
        String now = DateUtil.now();
        return now;
    }


    @Override
    @Transactional
    public ResponseResult save(Commodity commodity,
                               Inventory inventory) {
        System.out.println(commodity);
        String UUID = IdUtil.simpleUUID();
        commodity.setId(UUID);
        commodity.setCreateTime(date());
        commodity.setUpdateTime(date());
        commodity.setStatus(CommodityStatusEnum.CHECKING.getCode());
        int row = commodityMapper.insert(commodity);
        inventory.setCommodityId(UUID);
        inventory.setTotalCount(commodity.getInventory());
        String[] split = commodity.getPhoto().split(",");
        for (String path: split) {
            String[] split1 = path.split("/");
            System.out.println("split1 = " + split1);
            UploadFile uploadFile = new UploadFile();
            uploadFile.setPath(path);
            uploadFile.setProductId(commodity.getId());
            uploadService.upload(uploadFile);
        }
        inventoryService.insert(inventory);
        if(row > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult deleteById(String id) {
        int row = commodityMapper.deleteById(id);
        inventoryService.delete(id);
        if(row > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult updateAwayStatusById(String id, Integer putawayStatus) {
        Commodity oldCommodity = commodityMapper.selectById(id);
        int row = commodityMapper.update(oldCommodity, new UpdateWrapper<Commodity>()
                .set("update_time",date())
                .set("putaway_status", putawayStatus)
                .eq("id", id));
        if(row > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    @Transactional
    public ResponseResult updateStatusById(String id, String vertifyName,int status) {
        Commodity oldCommodity = commodityMapper.selectById(id);
        int row = commodityMapper.update(oldCommodity, new UpdateWrapper<Commodity>()
                .set("update_time",date())
                .set("status",status)
                .eq("id", id));
        if (row >0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult getByOther(String commodityName, String commoditySubHead, Integer category, String brand, String specificType, String max, String min, Long pageNo, Long pageSize, Integer putawayStatus) {
        System.out.println("category = " + category);
        List<Commodity> commoditys = commodityMapper.getByOther(commodityName, commoditySubHead,category,
                brand,max,min,specificType,pageNo*pageSize,pageSize,putawayStatus);
        if (commoditys.size()>0){
            return new ResponseResult("200","success",commoditys);
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult getLimit( Long pageNo,Long pageSize,Integer putawayStatus) {
//        IPage<Commodity> page = new Page<>(pageNo,pageSize);
//        IPage<Commodity> result = commodityMapper.selectPage(page,new QueryWrapper<Commodity>().eq("putaway_status",putawayStatus));
//        if (result.getRecords() != null){
//            return new ResponseResult("200","success",result);
//        }
//        return ResponseResult.error();
        List<Commodity> limit = commodityMapper.getLimit(pageNo, pageSize, putawayStatus);
        if (limit.size() >0){
            return new ResponseResult("200","success",limit);
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult selectOne(String id) {
        Commodity commodity = commodityMapper.selectOne(new QueryWrapper<Commodity>().eq("id", id));
        System.out.println("commodity = " + commodity);
        if (commodity != null){
            return new ResponseResult("200","success",commodity);
        }
        return ResponseResult.error();
    }

    public ResponseResult selectAll(){
        List<Commodity> commodities = commodityMapper.selectAll();
        if (commodities.size()>0){
            for (Commodity commodity:commodities){
                System.out.println("commodity = " + commodity);
            }
            return new ResponseResult("200","success",commodities);
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult update(Commodity commodity) {
        commodity.setUpdateTime(DateUtil.now());
        int row = commodityMapper.updateById(commodity);
        if (row >0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    /**
     * 根据类别ID查询商品
     * @param category
     * @return
     */
    @Override
    public ResponseResult queryCategory(Integer category) {
        List<Commodity> list = commodityMapper.selectList(new QueryWrapper<Commodity>().eq("category", category));
        return new ResponseResult("200", "success", list);
    }

    @Override
    public ResponseResult getByCategory(Integer category, int count) {
        List<Commodity> list = commodityMapper.getByCategoryCount(category, count);
        return new ResponseResult("200", "success", list);
    }

    public ResponseResult selGroupId(String[] id){
        List<Commodity> commodities = commodityMapper.selByGroupId(id);
        if (commodities.size()>0){
            for (Commodity commodity:commodities){
                System.out.println("commodity = " + commodity);
            }
            return new ResponseResult("200","success",commodities);
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult selLikeName(String commodityName) {
        List<Commodity> commodities = commodityMapper.selLikeName(commodityName);
        if (commodities.size()>0){
            for (Commodity commodity:commodities){
                System.out.println("commodity = " + commodity);
            }
            return new ResponseResult("200","success",commodities);
        }
        return ResponseResult.error();
    }

}
