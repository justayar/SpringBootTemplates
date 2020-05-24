package com.justayar.springboot.util.cache;

import com.justayar.springboot.domain.StudentCacheObject;

import java.util.List;

public abstract class RedisCacheManager {

    public abstract StudentCacheObject getObjectFromHashWithKey(String key);

    public abstract String getSingleFieldFromHashWithKey(String key, String valueType);

    public abstract void updateObjectInHashWithKey(String key, StudentCacheObject studentCacheObject);

    public abstract void setNewObjectToHashWithKey(StudentCacheObject studentCacheObject);

    public abstract void removeSingleValueFromHashWithKey(String key, String removedType);

    public abstract void removeObjectFromHashWithKey(String key);

    public abstract List<StudentCacheObject> getAllObjects();

}
