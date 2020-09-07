package com.cloud.smy.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;
import java.time.Duration;


@Configuration
public class RedisConfig {
    static final Duration EXPIRE_TIME = Duration.ofDays(3);


//    /**
//     * 自定义序列化redisTemplate
//     * @param
//     * @return
//     */
//    @Bean
//    public RedisTemplate<String, Serializable> serializableRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
//        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
//        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
//        return redisTemplate;
//    }
//
//    /**
//     * RedisTemplate配置
//     */
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
//        // 设置序列化
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
//        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//
//        // 配置redisTemplate
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
}
