package com.ran;

import redis.clients.jedis.Jedis;

/**
 * @Author 45010
 * @create 2021/2/2 23:07
 */
public class TestBitMap {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        System.out.println("设置位图偏移量");
        jedis.setbit("sign",0,false);
        jedis.setbit("sign",1,true);
        jedis.setbit("sign",2,false);
        jedis.setbit("sign",3,false);
        jedis.setbit("sign",4,true);
        System.out.println("获取位图sign,偏移量为0的值"+jedis.getbit("sign",0));
        //获取位图为true的数量
        System.out.println("位图sign的数量为:"+jedis.bitcount("sign"));

    }

}
