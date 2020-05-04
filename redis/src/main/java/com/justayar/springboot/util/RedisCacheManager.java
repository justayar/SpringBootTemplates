package com.justayar.springboot.util;

import com.justayar.springboot.configuration.RedisConfiguration;
import com.justayar.springboot.constants.RedisDemoConstants;
import com.justayar.springboot.domain.StudentCacheObject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@Component
public class RedisCacheManager implements InitializingBean {

    @Autowired
    RedisConfiguration redisConf;

    private Jedis redisClient;


    @Override
    public void afterPropertiesSet() {

        JedisPool jedisPool = redisConf.getJedisPool();

        redisClient = jedisPool.getResource();

    }

    public StudentCacheObject getAllValuesFromHashWithKey(String key){

        Map<String, String> redisMap = redisClient.hgetAll(key);

        return null;

    }

    public Object getValueFromHashWithKey(String key,String valueType){

        return redisClient.hget(key,valueType);
    }

    public void setSingleValueToHashWithKey(String key,String valueType,String value){

        redisClient.hset(key,valueType,value);
    }

    public void setAllValuesToHashWithKey(String key,StudentCacheObject studentCacheObject){

    }

    public void removeSingleValueFromHashWithKey(String key, String removedType){

        redisClient.hdel(key,removedType);
    }

    public void removeObjectFromHashWithKey(String key){

    }

    private Object getCacheField(String type, StudentCacheObject studentCacheObject){

        switch(type){

            case RedisDemoConstants
                    .STUDENT_CACHE_OBJECT_NAME_FIELD:
                return studentCacheObject.getName();
            case RedisDemoConstants.STUDENT_CACHE_OBJECT_EMAIL_FIELD:
                return studentCacheObject.getEmail();
            case RedisDemoConstants.STUDENT_CACHE_OBJECT_MAJOR_FIELD:
                return studentCacheObject.getMajor();
            case RedisDemoConstants.STUDENT_CACHE_OBJECT_GPA_FIELD:
                return studentCacheObject.getGpa();
            case RedisDemoConstants.STUDENT_CACHE_OBJECT_HAS_SCHOLAR_FIELD:
                return studentCacheObject.isHasScholar();
            default:
                return null;
        }
    }
}
