package com.smy.shop.service.impl;

import com.smy.shop.service.JedisService;
import io.micrometer.core.instrument.util.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


@Service
public class JedisServiceImpl implements JedisService {

    private JedisPool jedisPool;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.host")
    private String host;

    private Jedis getResource() {
        if(jedisPool==null) {
            jedisPool = new JedisPool(host , port);
        }
        Jedis resource = jedisPool.getResource();
        if (StringUtils.isBlank(password)) {
            return resource;
        } else {
            resource.auth(password);
            return resource;
        }
    }

    @Override
    public String get(String key) {

        Jedis resource = getResource();

        String string = resource.get(key);

        resource.close();

        return string;

    }

    @Override
    public String set(String key, String value) {

        Jedis resource = getResource();

        String string = resource.set(key, value);

        resource.close();

        return string;

    }

    @Override
    public String hget(String hkey, String key) {

        Jedis resource = getResource();

        String string = resource.hget(hkey, key);

        resource.close();

        return string;

    }

    @Override
    public long hset(String hkey, String key, String value) {

        Jedis resource = getResource();

        Long hset = resource.hset(hkey, key, value);

        resource.close();

        return hset;

    }

    @Override
    public long incr(String key) {

        Jedis resource = getResource();

        Long incr = resource.incr(key);

        resource.close();

        return incr;

    }

    @Override
    public long expire(String key, Integer second) {

        Jedis resource = getResource();

        Long expire = resource.expire(key, second);

        resource.close();

        return expire;

    }

    @Override
    public long ttl(String key) {

        Jedis resource = getResource();

        Long ttl = resource.ttl(key);

        resource.close();

        return ttl;
    }

    @Override
    public long del(String key) {

        Jedis resource = getResource();

        Long del = resource.del(key);

        resource.close();

        return del;
    }

    @Override
    public long hdel(String hkey, String key) {

        Jedis resource = getResource();

        Long hdel = resource.hdel(hkey, key);

        resource.close();

        return hdel;
    }
}
