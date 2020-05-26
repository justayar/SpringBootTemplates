package com.justayar.springboot.configuration;

import com.justayar.springboot.constants.RedisDemoConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import java.util.HashSet;
import java.util.Set;


@Configuration
public class RedisClusterConfiguration extends RedisConfiguration {

    @Autowired
    private AppConfiguration appConf;


    @Bean
    public JedisCluster getJedisCluster() {

        if (appConf.getActiveRedisMode().equalsIgnoreCase(RedisDemoConstants.REDIS_CLUSTER_MODE)) {

            String[] clusterNodes = appConf.getRedis().getCluster().getHost().split(";");


            Set<HostAndPort> clusters = new HashSet<>();


            for (String clusterNode : clusterNodes) {

                if (clusterNode != null &&
                        !clusterNode.isEmpty()) {

                    String[] clusterNodeEndpoint = clusterNode.split(":");

                    if (clusterNodeEndpoint.length == 2)
                        clusters.add(new HostAndPort(clusterNodeEndpoint[0], Integer.parseInt(clusterNodeEndpoint[1])));

                }

            }

            JedisPoolConfig jedisPoolConfig = getJedisPoolConfig();


            return new JedisCluster(clusters,
                    appConf.getRedis().getCluster().getTimeout(),
                    appConf.getRedis().getCluster().getTimeout(), jedisPoolConfig);
        }

        return null;

    }



}

