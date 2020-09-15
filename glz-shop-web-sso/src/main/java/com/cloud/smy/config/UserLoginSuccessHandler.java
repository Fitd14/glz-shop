package com.cloud.smy.config;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.cloud.smy.util.JWTTokenUtil;
import com.glz.constant.HttpStatus;
import com.glz.model.MemberDTO;
import com.glz.model.ResponseResult;
import com.glz.model.UserDTO;
import com.glz.pojo.Member;
import com.glz.pojo.RoleMenu;
import com.glz.pojo.User;
import com.smy.shop.service.MemberService;
import com.smy.shop.service.RoleMenuService;
import com.smy.shop.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.http.entity.ContentType;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;


@Configuration
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Reference
    UserService userService;

    @Reference
    MemberService memberService;

    @Reference
    RoleMenuService roleMenuService;
    

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 组装JWT
        String username = authentication.getName();
        String token = JWTTokenUtil.createTokenByUsername(username);
        token = JWTConfig.tokenPrefix + token;

        String refreshToken = JWTTokenUtil.createRefreshTokenByUsername(username);

        User user = userService.selectByUsername(username);
        PrintWriter writer = null;
        Integer code = HttpStatus.OK;
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        if (ObjectUtil.isEmpty(user)){
            Member member = memberService.findByUsername(username);
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setUsername(member.getUsername());
            memberDTO.setToken(token);
            writer = response.getWriter();
            writer.write(JSON.toJSONString(new ResponseResult(code.toString(),"登陆成功",memberDTO)));
            writer.flush();
            writer.close();
            return;
        }
        RoleMenu roleMenu = roleMenuService.selectByUserId(user.getId());

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setToken(token);
        userDTO.setRefreshToken(refreshToken);
        userDTO.setRoleMenu(roleMenu);

        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        // response.setHeader("token", tokenStr);

        writer = response.getWriter();
        writer.write(JSON.toJSONString(new ResponseResult(code.toString(),"登陆成功",userDTO)));
        writer.flush();
        writer.close();
    }

}
