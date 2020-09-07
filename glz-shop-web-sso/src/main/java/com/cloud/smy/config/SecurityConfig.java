package com.cloud.smy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserLoginSuccessHandler userLoginSuccessHandler;

    @Autowired
    private MyJWTFailureHandler myJWTFailureHandler;

    @Autowired
    private MyJWTLogoutHandler myJWTLogoutHandler;

    @Autowired
    private MyJWTAuthorizationFilter myJWTAuthorizationFilter;

    @Autowired
    private MyValidateCodeFilter myValidateCodeFilter;

    @Autowired
    private MyAuthenticationEntryPointHandler myAuthenticationEntryPoint;

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启跨域配置
        http.cors();
        // 关闭csrf攻击保护，否则若未实现csrf保护的情况下，请求会异常，如：/api/auth的post请求
        http.csrf().disable();
        // 指定匿名放行资源(登录认证接口)
        http.authorizeRequests().antMatchers("/auth/**").permitAll();
        // 任何未匿名放行的URL都需要身份认证
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin().loginProcessingUrl("/auth").successHandler(userLoginSuccessHandler)
                .failureHandler(myJWTFailureHandler);
        // 注销成功处理器
        http.logout().logoutUrl("/logout").logoutSuccessHandler(myJWTLogoutHandler);
        // 添加鉴权验证过滤器
        http.addFilterBefore(myJWTAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        // 添加验证码处理过滤器
        http.addFilterBefore(myValidateCodeFilter, UsernamePasswordAuthenticationFilter.class);
        // 认证失败端点处理器（用来解决匿名用户访问无权限资源时的异常）
        http.exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint);
        // 鉴权失败处理器（用来解决认证过的用户访问无权限资源时的异常）
        http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);
        // 不需要session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 禁用缓存
        http.headers().cacheControl();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 静态资源放过ingore是完全绕过了spring security的所有filter，相当于不走spring security，效率更高
        web.ignoring().antMatchers("/file/**");
    }

    /**
     * 自定义认证资源，密码加密方式
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // builder.userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
