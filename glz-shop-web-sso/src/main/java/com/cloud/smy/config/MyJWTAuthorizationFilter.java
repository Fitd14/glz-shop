package com.cloud.smy.config;

import com.cloud.smy.util.JWTTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.One;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class MyJWTAuthorizationFilter extends OncePerRequestFilter {

    /**
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取header
        String tokenHeader = request.getHeader(JWTTokenUtil.TOKEN_HEADER);
        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null || !tokenHeader.startsWith(JWTTokenUtil.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        // 如果请求头中有token，则进行解析，并且设置认证信息
        String token = tokenHeader.replace(JWTTokenUtil.TOKEN_PREFIX, "");
        try {
            String username = JWTTokenUtil.getUsernameFromToken(token);
            UsernamePasswordAuthenticationToken authenticationToken = null;
            if (username != null) {
                //UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
//                authenticationToken =
//                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            }
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (Exception e) {
            // e.printStackTrace();
            log.info("token失效");
        }
        // 放行
        filterChain.doFilter(request, response);
    }
}
