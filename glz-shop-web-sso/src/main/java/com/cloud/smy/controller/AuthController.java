package com.cloud.smy.controller;

import com.cloud.smy.util.JWTTokenUtil;
import com.cloud.smy.util.SecurityUtils;
import com.glz.model.ResponseResult;
import com.smy.shop.service.UserService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Reference
    UserService userService;

    @Autowired
    SecurityUtils securityUtils;

    @GetMapping("/isUseCaptcha")
    @ResponseBody
    public ResponseResult<Boolean> checkUsername(HttpServletRequest request) throws IOException {
        return new ResponseResult("200","是否需要验证码",securityUtils.isUseValidateCode(request));
    }

    @GetMapping("/captcha")
    public ResponseResult<?> captcha(String captchaId){
        Map<String, String> map = securityUtils.createOrFlushValidateCode(captchaId);
        return new ResponseResult("200","验证码",map);
    }

    @GetMapping("/refresh")
    public ResponseResult<Map<String, String>> refresh(HttpServletRequest request) {
        String token = request.getHeader(JWTTokenUtil.TOKEN_HEADER);
        String username = null;
        if (token != null && token.startsWith(JWTTokenUtil.TOKEN_PREFIX)) {
            token = token.substring(JWTTokenUtil.TOKEN_PREFIX.length());
            username = JWTTokenUtil.getUsernameFromToken(token);
        }
        if (username == null) {
            return new ResponseResult<>("500","未知错误");
        }
        Map<String, String> map = new HashMap<>();
        String newToken = JWTTokenUtil.createTokenByUsername(username);
        String newRefreshToken = JWTTokenUtil.createRefreshTokenByUsername(username);
        map.put("token", JWTTokenUtil.TOKEN_PREFIX + newToken);
        map.put("refreshToken", JWTTokenUtil.TOKEN_PREFIX + newRefreshToken);
        return new ResponseResult("200","token",map);
    }




}
