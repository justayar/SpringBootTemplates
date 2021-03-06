
package com.justayar.springboot.configuration;

import com.justayar.springboot.constants.RedisDemoConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class RedisSentinelConfiguration extends RedisConfiguration {



    @Autowired
    public AppConfiguration appConf;


    @Bean
    public JedisSentinelPool getJedisSentinel(){

        if(appConf.getActiveRedisMode().equalsIgnoreCase(RedisDemoConstants.REDIS_SENTINEL_MODE)) {

            String[] sentinelNodes = appConf.getRedis().getSentinel().getHost().split(";");

            Set sentinels = new HashSet<>();


            for (String sentinelNode : sentinelNodes) {

                if (sentinelNode != null &&
                        !sentinelNode.isEmpty()) {
                    sentinels.add(sentinelNode);

                }

            }


            JedisPoolConfig jedisPoolConfig = getJedisPoolConfig();


            return new JedisSentinelPool("mymaster", sentinels, jedisPoolConfig, appConf.getRedis().getSentinel().getTimeout());

        }

        return null;
    }



}

