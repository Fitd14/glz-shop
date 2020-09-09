package com.smy.shop.controller;

import com.glz.model.ResponseResult;
import com.glz.pojo.Member;
import com.smy.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @PostMapping("/save")
    public ResponseResult save(Member member){
        return memberService.save(member);
    }

    @PutMapping("/modify/pw")
    public ResponseResult modifyPassword(String username,String password){
        return memberService.updatePassword(username,password);
    }

    @PutMapping("/modify/nk")
    public ResponseResult modifyNickname(String username,String nickname){
        return memberService.updateNickname(username,nickname);
    }

}

