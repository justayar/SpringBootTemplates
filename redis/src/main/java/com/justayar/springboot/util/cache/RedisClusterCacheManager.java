package com.justayar.springboot.util.cache;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.justayar.springboot.configuration.AppConfiguration;
import com.justayar.springboot.configuration.RedisClusterConfiguration;
import com.justayar.springboot.constants.RedisDemoConstants;
import com.justayar.springboot.domain.StudentCacheObject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.JedisCluster;

import java.util.*;

public class RedisClusterCacheManager implements InitializingBean, RedisCacheManager {

    @Autowired
    RedisClusterConfiguration redisConf;

    @Autowired
    private AppConfiguration appConf;

    private JedisCluster redisClient;

    @Override
    public void afterPropertiesSet() {

        if (appConf.getActiveRedisMode().equalsIgnoreCase(RedisDemoConstants.REDIS_CLUSTER_MODE)) {

            redisClient = redisConf.getJedisCluster();
        }
    }

    public StudentCacheObject getObjectFromHashWithKey(String key) {

        Map<String, String> redisMap = redisClient.hgetAll(key);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.convertValue(redisMap, StudentCacheObject.class);

    }

    public String getSingleFieldFromHashWithKey(String key, String valueType) {

        return redisClient.hget(key, valueType);
    }

    public void updateObjectInHashWithKey(String key, StudentCacheObject studentCacheObject) {

        if (studentCacheObject == null)
            throw new IllegalArgumentException("[(updateObjectInHashWithKey)] Student object cannot be null");

        if (key == null)
            throw new IllegalArgumentException("[(updateObjectInHashWithKey)] Key cannot be null");

        Map<String, String> studentObjectMap = convertStudentObjectToMapObject(studentCacheObject);

        redisClient.hmset(key, studentObjectMap);
    }

    private Map<String, String> convertStudentObjectToMapObject(StudentCacheObject studentCacheObject) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return objectMapper.convertValue(studentCacheObject, Map.class);
    }

    public void setNewObjectToHashWithKey(StudentCacheObject studentCacheObject) {

        if (studentCacheObject == null)
            throw new IllegalArgumentException("[(setNewObjectToHashWithKey)] Student object cannot be null");

        if (studentCacheObject.getStudentId() == null)
            throw new IllegalArgumentException("[(setNewObjectToHashWithKey)] Student id cannot be null");

        Map<String, String> studentObjectMap = convertStudentObjectToMapObject(studentCacheObject);

        redisClient.hmset(studentCacheObject.getStudentId(), studentObjectMap);

    }

    public void removeSingleValueFromHashWithKey(String key, String removedType) {

        redisClient.hdel(key, removedType);
    }

    public void removeObjectFromHashWithKey(String key) {

        redisClient.hdel(key, "studentId", "name", "email", "major", "gpa");
    }

    public List<StudentCacheObject> getAllObjects() {

        List<StudentCacheObject> studentCacheObjectList = new ArrayList<>();

        Set<String> keys = redisClient.keys("*");
        if (!CollectionUtils.isEmpty(keys)) {
            for (String key : keys) {

                Map<String, String> redisObjectMap = redisClient.hgetAll(key);

                ObjectMapper mapper = new ObjectMapper();

                studentCacheObjectList.add(mapper.convertValue(redisObjectMap, StudentCacheObject.class));
            }

            return studentCacheObjectList;
        }

        return Collections.EMPTY_LIST;
    }


}