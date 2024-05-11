package com.example.cachingdemo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    private static final Logger log = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    RedisTemplate redisTemplate;

    public <T> T get(String key,Class<T> className){
        try {
            Object o = redisTemplate.opsForValue().get(key);
            if(o == null){
                return null;
            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(o.toString(),className);
        }catch (Exception e){
            log.error("Exception ",e);
            return null;
        }
    }


    public void set(String key,Object o, Long ttl){
        try {
            redisTemplate.opsForValue().set(key,o,ttl, TimeUnit.MINUTES);
        }catch (Exception e){
            log.error("Exception ",e);
        }
    }
}
