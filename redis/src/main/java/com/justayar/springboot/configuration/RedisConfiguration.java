package com.justayar.springboot.configuration;

import redis.clients.jedis.JedisPoolConfig;

public abstract class RedisConfiguration {

    public JedisPoolConfig getJedisPoolConfig(){

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        jedisPoolConfig.setBlockWhenExhausted(false);  //Setting this to false means an error will occur immediately when a client requests a connection and none are available, default is true
        jedisPoolConfig.setMaxIdle(15);  //The maximum number of idle connections that can be held in the pool,default is 8.
        jedisPoolConfig.setMinIdle(1);  //The target for the minimum number of idle connections to maintain in the pool,default is 0
        jedisPoolConfig.setMaxTotal(20);  // The maximum number of connections that can be allocated by the pool, default is 8.
        jedisPoolConfig.setMaxWaitMillis(20);

        return jedisPoolConfig;

    }
}
