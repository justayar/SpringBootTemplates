package com.justayar.springboot.controller;

import com.justayar.springboot.configuration.AppConfiguration;
import com.justayar.springboot.constants.RedisDemoConstants;
import com.justayar.springboot.domain.StudentCacheObject;
import com.justayar.springboot.util.cache.RedisCacheManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author canemreayar
 */

@RestController
@RequestMapping("/student")
public class StudentController implements InitializingBean {

    private RedisCacheManager redisCacheManager;

    @Autowired
    private RedisCacheManager redisStandaloneCacheManager;

    @Autowired
    private RedisCacheManager redisSentinelCacheManager;

    @Autowired
    private RedisCacheManager redisClusterCacheManager;

    @Autowired
    private AppConfiguration appConf;



    @Override
    public void afterPropertiesSet() throws Exception {

        if(appConf.getActiveRedisMode().equalsIgnoreCase(RedisDemoConstants.REDIS_SENTINEL_MODE)){
            redisCacheManager = redisSentinelCacheManager;
        }else if( appConf.getActiveRedisMode().equalsIgnoreCase(RedisDemoConstants.REDIS_CLUSTER_MODE)){
            redisCacheManager = redisClusterCacheManager;
        }else{
            redisCacheManager = redisStandaloneCacheManager;
        }

    }


    @PostMapping("/addNewStudent")
    public String addNewStudent(StudentCacheObject studentCacheObject) {

        redisCacheManager.setNewObjectToHashWithKey(studentCacheObject);

        return "OK";
    }


    @GetMapping("/getStudentInfo")
    public StudentCacheObject getStudentInfo(@RequestParam String studentId) {

        StudentCacheObject studentCacheObject = redisCacheManager.getObjectFromHashWithKey(studentId);

        if(studentCacheObject.getStudentId() == null)
            throw new NullPointerException("Student does not exist on system.");

        return studentCacheObject;
    }

    @GetMapping("/getStudentSingleField")
    public String getStudentSingleField(@RequestParam String studentId,@RequestParam String fieldType) {

        return redisCacheManager.getSingleFieldFromHashWithKey(studentId,fieldType);

    }

    @GetMapping("/getAllStudents")
    public List<StudentCacheObject> getAllStudents(){

        return redisCacheManager.getAllObjects();

    }

    @PutMapping("/updateStudentInfo")
    public String updateStudentInfo(@RequestParam String studentId,
                                    StudentCacheObject studentCacheObject) {

        redisCacheManager.updateObjectInHashWithKey(studentId, studentCacheObject);

        return "OK";

    }

    @DeleteMapping("/removeStudent")
    public String removeStudent(@RequestParam String studentId) {

        redisCacheManager.removeObjectFromHashWithKey(studentId);

        return "OK";
    }


}
