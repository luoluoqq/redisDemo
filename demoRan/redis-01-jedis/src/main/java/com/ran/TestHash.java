package com.ran;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 45010
 * @create 2021/2/2 23:06
 */
public class TestHash {
    /**
     * hash变更的数据 user： name age，更适合对象的存储。
     * String更适合存字符串
     * @param args
     */
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.0.103",6379);
        jedis.flushDB();
        Map map = new HashMap<String,String>();
        map.put("address","新乡");
        map.put("age","18");
        System.out.println("hash的设置值:"+jedis.hset("myhash","name","boran"));
        System.out.println("hash设置map:"+jedis.hset("myhash",map));
        System.out.println("hash设置多个值:"+jedis.hmset("myhash",map));
        System.out.println("hash的获取所有值:"+jedis.hgetAll("myhash"));
        System.out.println("hash的获取值:"+jedis.hget("myhash","name"));

        System.out.println("myhash中是否存在某个key:"+jedis.hexists("myhash","name"));
        System.out.println("myhash的长度:"+jedis.hlen("myhash"));
        System.out.println("myhash所有key"+jedis.hkeys("myhash"));
        System.out.println("myhash所有val"+jedis.hvals("myhash"));

        System.out.println("myhash 对不存在的key设置hsetnx 返回1:"+jedis.hsetnx("myhash1","count","1"));
        System.out.println("myhash 对存在的key设置hsetnx 返回0:"+jedis.hsetnx("myhash1","count","2"));
        System.out.println("myhash递增hincr  age"+jedis.hincrBy("myhash","age",10));
        System.out.println("myhash递减hincr  age"+jedis.hincrBy("myhash","age",-10));

        System.out.println("hash删除"+jedis.hdel("myhash","name","age","address"));

    }
}
