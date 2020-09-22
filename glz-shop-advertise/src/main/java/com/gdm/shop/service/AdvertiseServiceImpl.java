package com.gdm.shop.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gdm.shop.mapper.AdvertiseMapper;
import com.glz.pojo.Advertise;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class AdvertiseServiceImpl implements AdvertiseService{
    @Autowired
    AdvertiseMapper advertiseMapper;
    @Override
    public int createAdvertise(Advertise advertise) {
        if(advertise.getId()!=null){
            return advertiseMapper.updateById(advertise);
        }else {
            return advertiseMapper.insert(advertise);
        }
    }

    @Override
    public Advertise getAdvertiseId(String aid) {
        return advertiseMapper.selectById(aid);
    }

    @Override
    public List<Advertise> getList() {
        List<Advertise> advertises = advertiseMapper.getShowList();
        List<String> pics = new ArrayList<>();
        for (Advertise advertise:advertises) {
            pics.add(advertise.getPic());
        }
        return advertises;
    }

    @Override
    public List<Advertise> getAllList(Integer page,Integer rows) {
        return advertiseMapper.getAllList(page, rows);
    }

    @Override
    public int delete(String aid) {
        return advertiseMapper.deleteById(aid);
    }
}
