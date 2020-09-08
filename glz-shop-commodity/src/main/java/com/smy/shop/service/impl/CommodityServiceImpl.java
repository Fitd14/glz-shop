package com.smy.shop.service.impl;



import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glz.enums.CommodityStatusEnum;
import com.glz.model.ResponseResult;
import com.glz.pojo.Commodity;
import com.glz.pojo.CommodityAttribute;
import com.glz.pojo.CommodityCategory;
import com.smy.shop.mapper.CommodityMapper;
import com.smy.shop.service.CommodityCategoryService;
import com.smy.shop.service.CommodityService;
import com.smy.shop.service.ComodityAttributeService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.io.FileUtils;
import org.apache.dubbo.config.annotation.Service;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
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
    @Value("localtion")
    String localtion;
    public String date(){
        String now = DateUtil.now();
        return now;
    }

    public String upload(MultipartFile[] files) throws IOException {
        String url = null;
        for (MultipartFile file: files) {
            String id = UUID.randomUUID().toString().replace("-", "");
            String originalFilename = file.getOriginalFilename();
            String suffex = originalFilename.substring(originalFilename.lastIndexOf("."));
            String path= id+suffex;
            if (url == null){
                url = path;
            }else {
                url = url +"," + path;
            }
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File( localtion + File.separator +path));
        }
        return url;
    }

    @Override
    @GlobalTransactional
    public ResponseResult save(Commodity commodity, CommodityAttribute commodityAttribute, CommodityCategory commodityCategory) {
        commodity.setCreateTime(date());
        commodity.setUpdateTime(date());
        commodity.setStatus(CommodityStatusEnum.CHECKING.getCode());
        comodityAttributeService.add(commodityAttribute);
        commodityCategoryService.add(commodityCategory);
        int row = commodityMapper.insert(commodity);
        if(row > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult deleteById(Long id) {
        int row = commodityMapper.deleteById(id);
        if(row > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult updateAwayStatusById(Long id, Integer putawayStatus) {
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
    public ResponseResult updateStatusById(Long id, Integer status) {
        Commodity oldCommodity = commodityMapper.selectById(id);
        int row = commodityMapper.update(oldCommodity, new UpdateWrapper<Commodity>()
                .set("update_time",date())
                .set("status", status)
                .eq("id", id));
        if (row >0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }



    @Override
    public ResponseResult getByOther(String commodityName, String commoditySubHead, String brand,String max, String min) {

        List<Commodity> commoditys = commodityMapper.getByOther(commodityName, commoditySubHead, brand,max,min);
        if (commoditys.size()>0){
            return new ResponseResult("200","success",commoditys);
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult getLimit( Long pageNo,Long pageSize,Integer putawayStatus) {
        IPage<Commodity> page = new Page<>(pageNo,pageSize);
        IPage<Commodity> result = commodityMapper.selectPage(page,new QueryWrapper<Commodity>().eq("putaway_status",putawayStatus));
        if (result.getRecords() != null){
            return new ResponseResult("200","success",result);
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult selectOne(Long id) {
        Commodity commodity = commodityMapper.selectOne(new QueryWrapper<Commodity>().eq("id", id));
        if (commodity != null){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }


}
