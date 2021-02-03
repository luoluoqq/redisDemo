package com.ran;

import redis.clients.jedis.Jedis;

/**
 * @Author 45010
 * @create 2021/2/2 23:06
 */
public class TestSet {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.0.103",6379);
        jedis.flushDB();
        //set无需不能重复
        System.out.println("set 设置key:"+jedis.sadd("k1","v1","v1","v2","v3"));
        System.out.println("set获取key的元素:"+jedis.smembers("k1"));
        System.out.println("set获取key的元素个数:"+jedis.scard("k1"));
        System.out.println("set判断是否包含某个值:"+jedis.sismember("k1","v2"));
        //set随机获取元素 可以用作抽奖场景
        System.out.println("随机获取k1中的一个元素:"+jedis.srandmember("k1",1));

        //移动k1中的元素v3 到 k2（不存在会新建）中
        System.out.println("set 移动元素到新的set:"+jedis.smove("k1","k2","v3"));

        System.out.println("set随机弹出元素:"+jedis.spop("k1"));

        System.out.println("set移除元素:"+jedis.srem("k1","v1","v2"));


        System.out.println(jedis.sadd("user1","1","2","3","4","5","6","7"));
        System.out.println(jedis.sadd("user2","9","10","11","12","13","6","7"));

        //交集
        System.out.println("user1 和 user2 的共同元素:"+jedis.sinter("user1","user2"));
        //并集 共同好友 user1存的是他的好友id集，user2一样
        System.out.println("user1 和 user2 的合并元素:"+jedis.sunion("user1","user2"));
        //差集 返回一个集合与给定集合的差集的元素
        System.out.println("user1 与 user2 的不同元素:"+jedis.sdiff("user1","user2"));


    }
}
