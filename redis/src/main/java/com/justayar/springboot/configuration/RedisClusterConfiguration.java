package com.justayar.springboot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import java.util.HashSet;
import java.util.Set;


@Configuration
public class RedisClusterConfiguration {

    @Autowired
    public AppConfiguration appConf;


   /* TODO comment out below block if you run application cluster mode.



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


    private JedisPoolConfig getJedisPoolConfig(){

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        jedisPoolConfig.setBlockWhenExhausted(false);  //Setting this to false means an error will occur immediately when a client requests a connection and none are available, default is true
        jedisPoolConfig.setMaxIdle(15);  //The maximum number of idle connections that can be held in the pool,default is 8.
        jedisPoolConfig.setMinIdle(1);  //The target for the minimum number of idle connections to maintain in the pool,default is 0
        jedisPoolConfig.setMaxTotal(20);  // The maximum number of connections that can be allocated by the pool, default is 8.
        jedisPoolConfig.setMaxWaitMillis(20);

        return jedisPoolConfig;

    }

    */


}

