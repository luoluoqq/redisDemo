package com.ran;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ListPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 45010
 * @create 2021/2/2 23:06
 */
public class TestList {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.0.103",6379);
        //list设置值 要现有目标key 并且有对应的元素 否则报错
        jedis.flushDB();
        System.out.println(jedis.lpush("k1","a"));
        System.out.println(jedis.lset("k1",0,"b"));
        //list获取值
        System.out.println("获取k1的全部元素:"+jedis.lrange("k1",0,-1));
        System.out.println("删除list K1 中指定值指定个数的值 精准匹配"+jedis.lrem("k1",1,"a"));
        System.out.println("list k1的长度:"+jedis.llen("k1"));
        System.out.println("获取指定下标的元素:"+jedis.lindex("k1",0));
        System.out.println("指定位置插入元素:"+jedis.linsert("k1", ListPosition.AFTER,"b","rr"));
        System.out.println("截断数组,数组将被改变:"+jedis.ltrim("k1",0,1));
        //list 做栈（先进后出） lpush lpop 配对使用
        jedis.lpush("k2","a");
        jedis.lpush("k2","b");
        jedis.lpush("k2","c");
        System.out.println("k2的全部元素:"+jedis.lrange("k2",0,-1));
        jedis.lpop("k2");
        System.out.println("k2弹出最后一个元素lpop后的元素:"+jedis.lrange("k2",0,-1));

        //list 做队列（先进先出）lpush rpop 配对使用
        jedis.lpush("k3","a");
        jedis.lpush("k3","b");
        jedis.lpush("k3","c");
        System.out.println("k3的全部元素:"+jedis.lrange("k3",0,-1));
        jedis.rpop("k3");
        System.out.println("k3弹出第一个元素rpop后的元素:"+jedis.lrange("k3",0,-1));

        System.out.println("组合命令，先弹出列表第一个元素，放到新的列表中:"+jedis.rpoplpush("k3","k4"));
        System.out.println("rpoplpush后k3的元素:"+jedis.lrange("k3",0,-1));
        System.out.println("rpoplpush后k4的元素:"+jedis.lrange("k4",0,-1));
    }
}
