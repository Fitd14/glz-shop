package com.smy.shop.service;

import com.glz.model.MemberDTO;
import com.glz.model.ResponseResult;
import com.glz.pojo.Member;

public interface MemberService {

    ResponseResult save(Member member);

    ResponseResult updatePassword(String username,String password);

    Member findByUsername(String username);

    ResponseResult updateNickname(String username,String nickname);
    
    ResponseResult findAll();

    ResponseResult findMemberInfoByUsername(String username);

    ResponseResult modifyInfo(MemberDTO memberDTO);
}
