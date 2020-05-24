package com.justayar.springboot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.*;

@Configuration
public class RedisStandaloneConfiguration extends RedisConfiguration {

    @Autowired
    public AppConfiguration appConf;


    @Bean
    public JedisPool getJedisStandalone(){

        JedisPoolConfig jedisPoolConfig = getJedisPoolConfig();


        return new JedisPool(jedisPoolConfig,
                appConf.getRedis().getStandalone().getHost(),
                appConf.getRedis().getStandalone().getPort(),
                appConf.getRedis().getStandalone().getTimeout());

    }


}
