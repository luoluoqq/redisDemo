package com.ran.redis02springboot;

import com.ran.redis02springboot.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Redis02SpringbootApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
        //opsForValue  操作string
        //opsForGeo 操作geo
        //opsForList
        //opsForSet
        //opsForZSet
        //opsForHash
        redisTemplate.opsForValue().set("name","博然");
        System.out.println(redisTemplate.opsForValue().get("name"));

        //获取redis连接对象
        //RedisConnectionFactory redisConnection = redisTemplate.getConnectionFactory();
        //RedisConnection redis = redisConnection.getConnection();
        //redis.flushAll();
        //redis.flushDb();
    }
    @Test
    public void test1(){
        redisTemplate.opsForValue().set("address","新乡");
        System.out.println(redisTemplate.opsForValue().get("address"));
    }

    @Test
    public void test2(){
        redisUtil.set("hello","小慕聪");
        redisUtil.expire("hello",100);
        System.out.println(redisUtil.get("hello"));

    }

}
