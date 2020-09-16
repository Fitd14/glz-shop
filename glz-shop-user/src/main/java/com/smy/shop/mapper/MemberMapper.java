package com.smy.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glz.model.MemberDTO;
import com.glz.pojo.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberMapper extends BaseMapper<Member> {
    MemberDTO findMemberInfo(String username);

    List<MemberDTO> findAll();
}
