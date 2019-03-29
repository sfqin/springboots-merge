package cn.zzzcr.springboots.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    StringRedisTemplate redisTemplate;

//    类型String，List,Hash,Set,ZSet
//    对应的方法分别是opsForValue()、opsForList()、opsForHash()、opsForSet()、opsForZSet()

    @RequestMapping("save")
    public Object save(@Param("key") String key, @Param("value") String value){
        System.out.println("save");
        redisTemplate.opsForValue().set(key,value);

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();


        objectObjectHashMap.put(key,value);

        return objectObjectHashMap;
    }

    @RequestMapping("get")
    public Object get(@Param("key") String key){
        System.out.println("get");
        String value = redisTemplate.opsForValue().get(key);

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();

        objectObjectHashMap.put(key,value);


        return objectObjectHashMap;
    }

}
