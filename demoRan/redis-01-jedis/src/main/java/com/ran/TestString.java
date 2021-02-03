package com.ran;

import redis.clients.jedis.Jedis;

/**
 * @Author 45010
 * @create 2021/2/2 23:06
 */
public class TestString {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.0.103",6379);

        System.out.println("设置key为name 值为ranran"+jedis.set("name","ranran"));
        System.out.println("获取{name}的值:"+jedis.get("name"));
        System.out.println("批量设置{age}{address}:"+jedis.mset("age","18","address","上海"));
        System.out.println("批量获取{age}{address}:"+jedis.mget("name","age","address"));

        System.out.println("返回是否存在某个key"+jedis.exists("address"));
        System.out.println("存在key 不设置值 返回0"+jedis.setnx("age","100"));
        System.out.println("删除{address}"+jedis.del("age"));
        //可用作分布式锁 如果存在key 返回0  如果不存在key 返回 1 并设置值
        System.out.println("不存在key 设置值 返回1:"+jedis.setnx("age","100"));

        System.out.println("获取{address}的长度"+jedis.strlen("address"));
        System.out.println("设置{address}有效时间，并设置值"+jedis.setex("address",10,"新乡"));
        System.out.println("获取{address}的有效时间"+jedis.ttl("address"));

        System.out.println("更新{name}对应下标的值之后的值（包含当前位置）"+jedis.setrange("name",0 ,"hello"));
        System.out.println("获取{address}对应区间的值，闭区间，类似于substr"+jedis.getrange("address",0L,-1L));

        System.out.println("先获取后更新{address}:"+jedis.getSet("address","中国"));
        System.out.println("先获取后更新{address}:"+jedis.get("address"));

    }
}
