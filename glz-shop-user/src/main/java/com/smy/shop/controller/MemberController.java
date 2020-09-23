package com.smy.shop.controller;

import cn.hutool.extra.mail.MailUtil;
import com.glz.model.MemberDTO;
import com.glz.model.ResponseResult;
import com.glz.pojo.Member;
import com.glz.util.MailUtils;
import com.smy.shop.service.MemberService;
import com.smy.shop.utils.JWTTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user/member")
public class MemberController {

    @Autowired
    MemberService memberService;


    @PostMapping("/save")
    public ResponseResult save(@RequestBody Member member){
        System.out.println(member);
        return memberService.save(member);
    }

    @PutMapping("/modify/pw")
    public ResponseResult modifyPassword(@RequestBody String username,@RequestBody String password){
        return memberService.updatePassword(username,password);
    }

    @PutMapping("/modify/nk")
    public ResponseResult modifyNickname(@RequestBody String username,@RequestBody String nickname){
        return memberService.updateNickname(username,nickname);
    }

    @GetMapping("/getAll")
    public ResponseResult getAll(){
        return memberService.findAll();
    }

    @GetMapping("/getInfo")
    public ResponseResult getUserInfo(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String username = JWTTokenUtil.getUsernameFromToken(token);
        return memberService.findMemberInfoByUsername(username);
    }

    @GetMapping("/info/{username}")
    public ResponseResult getUserInfoByUsername(@PathVariable String username){
        return memberService.findMemberInfoByUsername(username);
    }

    @GetMapping("/forget/pw")
    public ResponseResult forgetPassword(@RequestBody String username){
        Member member = memberService.findByUsername(username);
        MailUtils.sendForgotPassword(member.getEmail());
        return new ResponseResult("200","邮件已发送，请查收");
    }

    @PutMapping("/modify/info")
    public ResponseResult modifyInfo(@RequestBody MemberDTO memberDTO){
        return memberService.modifyInfo(memberDTO);
    }
}

