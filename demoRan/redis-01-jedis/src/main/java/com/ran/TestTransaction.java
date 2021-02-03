package com.ran;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @Author 45010
 * @create 2021/2/3 14:47
 */
public class TestTransaction extends Thread{


    @Override
    public void run() {
        Jedis otherJedis =  new Jedis("192.168.0.103",6379);
        System.out.println("其它线程设置money的值为1000"+otherJedis.set("money","1000"));
    }

    public static void main(String[] args) throws InterruptedException {
        Transaction transaction = null;

        //获取jedis
        Jedis jedis = new Jedis("192.168.0.103",6379);
        //正常的事务
        System.out.println("开启事务");
        try {
            transaction = jedis.multi();
            System.out.println("命令入队:"+transaction.setnx("k1","v1"));
            System.out.println("命令入队:"+transaction.setnx("k2","v2"));
            System.out.println("命令入队:"+transaction.setnx("k3","v3"));
            System.out.println("执行事务:"+transaction.exec());
        }catch (Exception e){
            transaction.discard();//异常放弃事务
            e.printStackTrace();
        }finally {
            //jedis.close();//关闭连接
        }


        System.out.println("清空当前库:"+jedis.flushDB());

        //事务中存在运行时异常，则异常的指令不能生效其余的指令可以生效。
        //所以说redis事务不保证原子性。 单条指令是保证原子性的
        System.out.println("开启事务");
        transaction = jedis.multi();
        System.out.println("命令入队:"+transaction.setnx("k1","v1"));
        System.out.println("命令入队:"+transaction.setnx("k2","v2"));
        System.out.println("命令入队:"+transaction.setnx("k3","v3"));
        System.out.println("命令入队:"+transaction.set("count","aaa"));
        System.out.println("运行时异常如incr字符串:"+transaction.incrBy("count",10));
        System.out.println("执行事务:"+transaction.exec());
        System.out.println("事务中的运行时异常的操作不能生效:"+jedis.get("count"));

        System.out.println("清空当前库:"+jedis.flushDB());

        //取消事务
        System.out.println("开启事务");
        transaction = jedis.multi();
        System.out.println("命令入队:"+transaction.setnx("k1","v1"));
        System.out.println("取消事务:"+transaction.discard());

        System.out.println("清空当前库:"+jedis.flushDB());
        //redis实现乐观锁  watch
        System.out.println("设置金钱100:"+jedis.set("money","100"));
        System.out.println("设置支出0:"+jedis.set("out","0"));
        //监控money watch 如果money的值有被更新过，则事务中相命令都不会生效
        System.out.println("监控money:"+jedis.watch("money"));
        System.out.println("开始事务");
        transaction = jedis.multi();
        System.out.println("设置name:"+transaction.set("name","boran"));
        System.out.println("金钱减去20:"+transaction.incrBy("out",20));
        System.out.println("支出增加20:"+transaction.decrBy("money",20));
        //事务未执行时在另一个线程中，重新设置money的值
        TestTransaction testTransaction = new TestTransaction();
        testTransaction.start();
        Thread.sleep(2000);
        System.out.println("其它线程更改过money之后,执行事务，会出现并发问题，如果监控中的key被更改过，事务中的命令都不会生效"+transaction.exec());
        jedis.randomKey();
        jedis.close();
    }
}
