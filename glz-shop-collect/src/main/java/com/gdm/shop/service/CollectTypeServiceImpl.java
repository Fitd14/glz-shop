package com.gdm.shop.service;

import cn.hutool.core.date.DateUtil;
import com.gdm.service.CollectService;
import com.gdm.service.CollectTypeService;
import com.gdm.shop.mapper.CollectTypeMapper;
import com.glz.pojo.Collect;
import com.glz.pojo.CollectType;
import org.apache.commons.lang.time.DateUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class CollectTypeServiceImpl implements CollectTypeService {
    @Autowired
    CollectTypeMapper collectTypeMapper;
    @Autowired
    CollectService collectService;
    @Override
    public int createType(Long userId, String typeName) {
        CollectType collectType = new CollectType();
        collectType.setUserId(userId);
        collectType.setTypeName(typeName);
        collectType.setCreateTime(DateUtil.now());
        return collectTypeMapper.insert(collectType);
    }

    @Override
    public CollectType getId(Long id) {
        return collectTypeMapper.selectById(id);
    }

    @Override
    public List<CollectType> getList(Long userId) {
        return collectTypeMapper.getList(userId);
    }

    @Override
    public int delete(Long typeId,Long userId) {
        List<Collect> list = collectService.getListId(typeId,userId);
        if (list.isEmpty()){
            return collectTypeMapper.deleteById(typeId);
        }else {
            return 0;
        }
    }
}
