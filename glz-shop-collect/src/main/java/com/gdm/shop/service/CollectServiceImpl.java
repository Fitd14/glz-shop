package com.gdm.shop.service;

import cn.hutool.core.date.DateUtil;
import com.gdm.service.CollectService;
import com.gdm.shop.mapper.CollectMapper;
import com.gdm.shop.mapper.CollectTypeMapper;
import com.glz.model.ResponseResult;
import com.glz.pojo.Collect;
import com.glz.pojo.Commodity;
import com.smy.shop.service.CommodityService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


//@Service
@Service
@Component
public class CollectServiceImpl implements CollectService {
    @Autowired
    CollectMapper collectMapper;
    @Autowired
    CollectTypeMapper collectTypeMapper;
    @Reference
    CommodityService commodityService;
    @Override
    public int create(String cid,String uid) {
        Collect collect = new Collect();
        ResponseResult<Commodity> responseResult = commodityService.selectOne(cid);
        Commodity commodity =  responseResult.getData();
        collect.setUserId(uid);
        collect.setComId(cid);
        collect.setCommodityName(commodity.getCommodityName());
        collect.setPhoto(commodity.getPhoto());
        collect.setBrand(commodity.getBrand());
        collect.setTypeId("1302779453499269121");
        collect.setPrice(commodity.getPrice());
        collect.setCreateTime(DateUtil.now());
        collect.setNote(commodity.getProductDetail());
        return collectMapper.insert(collect);
    }

    @Override
    public Collect getId(Long cid) {
       return collectMapper.selectById(cid);
    }

    @Override
    public int delete(String cid) {
        return collectMapper.deleteById(cid);
    }

    @Override
    public List<Collect> getListIdPage(Long typeId,Long userId,int page) {
        return collectMapper.getListIdPage(typeId,userId,page);
    }

    @Override
    public List<Collect> getListId(Long typeId, Long userId) {
        return collectMapper.getListId(typeId,userId);
    }

    @Override
    public List<Collect> getList(String userId,int page) {
        return collectMapper.getList(userId,page);
    }

    @Override
    public void deletes(List<Long> ids) {
        collectMapper.deleteBatchIds(ids);
    }

    @Override
    public List<Collect> getListUid(String userId) {
        return collectMapper.getListUid(userId);
    }
}
