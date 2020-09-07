package com.cloud.smy.config;

import com.cloud.smy.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyValidateCodeFilter extends OncePerRequestFilter {

    @Autowired
    SecurityUtils securityUtils;

    @Autowired
    MyJWTFailureHandler myJWTFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response
        , FilterChain filterChain) throws ServletException, IOException {
            // 判断是否登录认证请求
        if ("/api/auth".equals(request.getRequestURI()) && "POST".equalsIgnoreCase(request.getMethod())) {
            // 判断当前登录是否需要验证码验证(根据用户名、ip、session)
//            if (securityUtils.isUseValidateCode(request)) {
//                String captchaId = ServletRequestUtils.getStringParameter(request, "captchaId");
//                String captchaCode = ServletRequestUtils.getStringParameter(request, "captchaCode");
//                // 进行验证码的校验
//                if (!securityUtils.isVerValidateCode(captchaId, captchaCode)) {
//                    // 如果校验不通过，调用自定义校验失败处理器
//                    myJWTFailureHandler.onAuthenticationFailure(request, response,
//                            new UsernameNotFoundException("验证码错误"));
//                    return;
//                }
//            }
        }
        // 放行
        filterChain.doFilter(request, response);
    }
}
