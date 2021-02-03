package com.ran;

import redis.clients.jedis.Jedis;

/**
 * @Author 45010
 * @create 2021/2/2 22:52
 */
public class TestPing {
    public static void main(String[] args) {
        //1. new 一个jedis对象
        Jedis jedis = new Jedis("127.0.0.1",6379);
        //jedis 所有的命令都是原生的e
        System.out.println(jedis.ping());
    }
}
