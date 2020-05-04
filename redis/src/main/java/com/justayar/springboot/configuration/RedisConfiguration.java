package com.justayar.springboot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfiguration {

    @Autowired
    public AppConfiguration appConf;


    @Bean
    public JedisPool getJedisPool(){

        return new JedisPool(new JedisPoolConfig(),
                appConf.getRedis().getHost(),
                appConf.getRedis().getPort(),
                appConf.getRedis().getTimeout());

    }
}
