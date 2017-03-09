package com.alogrithim.redis;

import java.util.Random;

import redis.clients.jedis.Jedis;

public class RedisLock {

    Jedis jedis = new Jedis("localhost", 6379);
    String key = "product_";
    String LOCKED = "locked";
    boolean lock = false;
    /**
     * 加锁 使用方式为： lock(); try{ executeMethod(); }finally{ unlock(); }
     * @param timeout timeout的时间范围内轮询锁
     * @param expire 设置锁超时时间
     * @return 成功 or 失败
     * 
     *http://blog.csdn.net/u010359884/article/details/50310387
     *
     *http://blog.csdn.net/qq_18860653/article/details/54907247
     */
    public boolean lock(long timeout, int expire) {
	

	long nanoTime = System.nanoTime();
	timeout *= 1000;
	try {
	    // 在timeout的时间范围内不断轮询锁
	    while (System.nanoTime() - nanoTime < timeout) {
		// 锁不存在的话，设置锁并设置锁过期时间，即加锁
		if (jedis.setnx(key, LOCKED) == 1) {
		    jedis.expire(key, expire);// 设置锁过期时间是为了在没有释放
		    // 锁的情况下锁过期后消失，不会造成永久阻塞
		    lock = true;
		    return lock;
		}
		System.out.println("出现锁等待");
		// 短暂休眠，避免可能的活锁
		Thread.sleep(3, new Random().nextInt(30));
	    }
	} catch (Exception e) {
	    throw new RuntimeException("locking error", e);
	}
	return false;
    }

    public void unlock() {
	try {
	    if (lock) {
		jedis.del(key);// 直接删除
	    }
	} catch (Throwable e) {

	}
    }
}
