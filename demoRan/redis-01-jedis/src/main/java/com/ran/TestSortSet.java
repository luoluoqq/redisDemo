package com.ran;

import redis.clients.jedis.Jedis;

/**
 * @Author 45010
 * @create 2021/2/2 23:06
 */
public class TestSortSet {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.0.103",6379);
        jedis.flushDB();
        //与set想比多了一个参数，序号,用这个来保证顺序
        System.out.println("zset的添加:"+jedis.zadd("k1",0,"v1"));
        System.out.println("zset的添加:"+jedis.zadd("k1",1,"v2"));
        System.out.println("zset的添加:"+jedis.zadd("k1",2,"v3"));
        System.out.println("zset的添加:"+jedis.zadd("k1",3,"v4"));
        System.out.println("zset的添加:"+jedis.zadd("k1",4,"v5"));
        System.out.println("zset的获取:"+jedis.zrange("k1",0,-1));
        System.out.println("zset的元素个数"+jedis.zcard("k1"));
        System.out.println("zset的数量，以score区间筛选"+jedis.zcount("k1",0,3));
        System.out.println("zset的排序（升序）:"+jedis.zrangeByScore("k1",0,4));
        System.out.println("zset的反排序（降序）:"+jedis.zrevrangeByScore("k1",4,0));
        System.out.println("zset获取元素的score:"+jedis.zscore("k1","v5"));
        System.out.println("zset迭代元素:"+jedis.zscan("k1","0"));
        System.out.println("zset的删除:"+jedis.zrem("k1","v1"));
    }
}
