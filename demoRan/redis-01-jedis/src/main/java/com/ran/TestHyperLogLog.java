package com.ran;

import redis.clients.jedis.Jedis;

/**
 * @Author 45010
 * @create 2021/2/2 23:07
 */
public class TestHyperLogLog {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.0.103",6379);

        System.out.println("为nums添加基数:"+jedis.pfadd("nums","1","2","3","4","5"));
        System.out.println("为nums1添加基数:"+jedis.pfadd("nums1","5","6","7","8","9"));
        System.out.println("nums基数为:"+jedis.pfcount("nums"));
        System.out.println("nums1基数为:"+jedis.pfcount("nums1"));
        System.out.println("合并nums1与nums:"+jedis.pfmerge("num2","nums","nums1"));
        System.out.println("num2的基数为:"+jedis.pfcount("num2"));

    }
}
