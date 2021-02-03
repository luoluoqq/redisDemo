package com.ran;

import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;

/**
 * @Author 45010
 * @create 2021/2/2 23:07
 */
public class TestGeo {
    /**
     * 地理半径
     * geoadd 添加位置信息
     * geodist 两个元素之间的距离
     * georadius 以指定坐标为中心，寻找方圆？km的位置
     * georadiusbymember 以geo中的元素的位置信息，寻找方圆？km的位置
     * geopos
     *
     * @param args
     */
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.0.103",6379);

        jedis.flushDB();
        System.out.println(jedis.geoadd("china:city",11,11,"bejing"));
        System.out.println(jedis.geoadd("china:city",22,22,"bejing"));
        System.out.println(jedis.geopos("china:city","beijing"));
        //GEO底层的实现原理是zset ，可以用zset操作geo
        System.out.println("可以使用zset操作geo 删除元素"+jedis.zrem("china:city","bejing"));
    }
}
