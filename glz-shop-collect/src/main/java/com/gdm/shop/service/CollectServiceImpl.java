package com.gdm.shop.service;

import cn.hutool.core.date.DateUtil;
import com.gdm.service.CollectService;
import com.gdm.shop.mapper.CollectMapper;
import com.gdm.shop.mapper.CollectTypeMapper;
import com.glz.pojo.Collect;
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
    @Override
    public int create(Long typeId,Long userId) {
        Collect collect = new Collect();
        collect.setUserId(userId);
        collect.setComId((long) 1);
        collect.setCommodityName("meizu手机");
        collect.setBrand("手机");
        collect.setNote("备注1");
        collect.setPhoto("地址2");
        collect.setTypeId(typeId);
        collect.setPrice(new BigDecimal(50.0));
        collect.setCreateTime(DateUtil.now());
        return collectMapper.insert(collect);
    }

    @Override
    public Collect getId(Long cid) {
       return collectMapper.selectById(cid);
    }

    @Override
    public int delete(Long cid) {
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
    public List<Collect> getList(Long userId,int page) {
        return collectMapper.getList(userId,page);
    }

    @Override
    public void deletes(List<Long> ids) {
        collectMapper.deleteBatchIds(ids);
    }
}
