package com.cloud.smy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = (60*30))
public class SSOApplication {
    public static void main(String[] args) {
        SpringApplication.run(SSOApplication.class,args);
    }
}
