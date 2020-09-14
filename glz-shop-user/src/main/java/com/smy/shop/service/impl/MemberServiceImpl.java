package com.smy.shop.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.glz.enums.ResultEnum;
import com.glz.model.ResponseResult;
import com.glz.pojo.Member;
import com.smy.shop.mapper.MemberMapper;
import com.smy.shop.service.MemberService;
import com.smy.shop.util.BCryptUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public ResponseResult save(Member member) {
        member.setId(IdUtil.simpleUUID());
        member.setCreateTime(DateUtil.now());
        member.setPassword(BCryptUtils.encodePassword(member.getPassword()));
        int result = memberMapper.insert(member);
        if (result > 0) {
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult updatePassword(String username,String password) {
        Member member = findByUsername(username);
        member.setUpdateTime(DateUtil.now());
        String encodePassword = BCryptUtils.encodePassword(password);
        int result = memberMapper.update(member, new UpdateWrapper<Member>()
                .set("password", encodePassword)
                .set("update_time",member.getUpdateTime())
                .eq("username", username));
        if (result > 0) {
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public Member findByUsername(String username) {
        System.out.println(username);
        return memberMapper.selectOne(new QueryWrapper<Member>().eq("username", username));
    }

    @Override
    public ResponseResult updateNickname(String username,String nickname) {
        Member member = findByUsername(username);
        member.setUpdateTime(DateUtil.now());
        int result = memberMapper.update(member, new UpdateWrapper<Member>()
                .set("nickname", nickname)
                .set("update_time",member.getUpdateTime())
                .eq("username", username));
        if (result > 0) {
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult findAll() {
        List<Member> members = memberMapper.selectList(new QueryWrapper<>());
        return new ResponseResult(ResultEnum.OK.getCode(),ResultEnum.OK.getValue(),members);
    }


}
