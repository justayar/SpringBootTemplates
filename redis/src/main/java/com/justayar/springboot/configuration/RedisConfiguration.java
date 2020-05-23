package com.justayar.springboot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class RedisConfiguration {

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

    /*

    @Bean
    public JedisSentinelPool getJedisSentinel(){

        String[] sentinelNodes = appConf.getRedis().getSentinel().getHost().split(";");

        Set sentinels = new HashSet<>();


        for(String sentinelNode : sentinelNodes){

            if(sentinelNode!= null &&
                    !sentinelNode.isEmpty()){
                sentinels.add(sentinelNode);

            }

        }


        JedisPoolConfig jedisPoolConfig = getJedisPoolConfig();


        return new JedisSentinelPool("mymaster",sentinels,jedisPoolConfig,appConf.getRedis().getSentinel().getTimeout());

    }



    @Bean
    public JedisCluster getJedisCluster(){

        String[] clusterNodes = appConf.getRedis().getCluster().getHost().split(";");


        Set<HostAndPort> clusters = new HashSet<>();


        for(String clusterNode : clusterNodes){

            if(clusterNode!= null &&
                    !clusterNode.isEmpty()){

                String[] clusterNodeEndpoint = clusterNode.split(":");

                if(clusterNodeEndpoint.length == 2)
                    clusters.add(new HostAndPort(clusterNodeEndpoint[0],Integer.parseInt(clusterNodeEndpoint[1])));

            }

        }

        JedisPoolConfig jedisPoolConfig = getJedisPoolConfig();


        return new JedisCluster(clusters,
                appConf.getRedis().getCluster().getTimeout(),
                appConf.getRedis().getCluster().getTimeout(),jedisPoolConfig);


    }


    */

    private JedisPoolConfig getJedisPoolConfig(){

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        jedisPoolConfig.setBlockWhenExhausted(false);  //Setting this to false means an error will occur immediately when a client requests a connection and none are available, default is true
        jedisPoolConfig.setMaxIdle(15);  //The maximum number of idle connections that can be held in the pool,default is 8.
        jedisPoolConfig.setMinIdle(1);  //The target for the minimum number of idle connections to maintain in the pool,default is 0
        jedisPoolConfig.setMaxTotal(20);  // The maximum number of connections that can be allocated by the pool, default is 8.
        jedisPoolConfig.setMaxWaitMillis(20);

        return jedisPoolConfig;

    }


}
