package com.justayar.springboot.util.cache;

import com.justayar.springboot.domain.StudentCacheObject;

import java.util.List;

public interface RedisCacheManager {

     StudentCacheObject getObjectFromHashWithKey(String key);

     String getSingleFieldFromHashWithKey(String key, String valueType);

     void updateObjectInHashWithKey(String key, StudentCacheObject studentCacheObject);

     void setNewObjectToHashWithKey(StudentCacheObject studentCacheObject);

     void removeSingleValueFromHashWithKey(String key, String removedType);

     void removeObjectFromHashWithKey(String key);

     List<StudentCacheObject> getAllObjects();

}
