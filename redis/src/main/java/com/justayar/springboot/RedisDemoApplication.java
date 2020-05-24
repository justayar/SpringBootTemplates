package com.justayar.springboot;

import com.justayar.springboot.configuration.AppConfiguration;
import com.justayar.springboot.util.cache.RedisCacheManager;
import com.justayar.springboot.util.cache.RedisClusterCacheManager;
import com.justayar.springboot.util.cache.RedisSentinelCacheManager;
import com.justayar.springboot.util.cache.RedisStandaloneCacheManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class RedisDemoApplication {

	@Bean
	@ConfigurationProperties
	public AppConfiguration appConfiguration() { return new AppConfiguration(); }

	@Bean
	public RedisCacheManager redisStandaloneCacheManager(){
		return new RedisStandaloneCacheManager();
	}

	@Bean
	public RedisCacheManager redisSentinelCacheManager(){
		return new RedisSentinelCacheManager();
	}

	@Bean
	public RedisCacheManager redisClusterCacheManager(){
		return new RedisClusterCacheManager();
	}

	public static void main(String[] args) {

		SpringApplication.run(RedisDemoApplication.class, args);
	}

}
