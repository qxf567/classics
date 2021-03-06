package com.alogrithim.redis;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.lang.time.StopWatch;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;

/**
 * 利用redis + lua解决抢红包高并发的问题
 * 
 * 抢红包
 * 
 * */
public class TestEval {
    static String host = "10.1.34.201";
    static int honBaoCount = 1_0_0000;

    static int threadCount = 20;

    static String hongBaoList = "hongBaoList";
    static String hongBaoConsumedList = "hongBaoConsumedList";
    static String hongBaoConsumedMap = "hongBaoConsumedMap";

    static Random random = new Random();

    // -- 函数：尝试获得红包，如果成功，则返回json字符串，如果不成功，则返回空
    // -- 参数：红包队列名， 已消费的队列名，去重的Map名，用户ID
    // -- 返回值：nil 或者 json字符串，包含用户ID：userId，红包ID：id，红包金额：money
    static String tryGetHongBaoScript =
    // "local bConsumed = redis.call('hexists', KEYS[3], KEYS[4]);\n"
    // + "print('bConsumed:' ,bConsumed);\n"
    "if redis.call('hexists', KEYS[3], KEYS[4]) ~= 0 then\n" + "return nil\n"
	    + "else\n"
	    + "local hongBao = redis.call('rpop', KEYS[1]);\n"
	    // + "print('hongBao:', hongBao);\n"
	    + "if hongBao then\n" + "local x = cjson.decode(hongBao);\n" + "x['userId'] = KEYS[4];\n"
	    + "local re = cjson.encode(x);\n" + "redis.call('hset', KEYS[3], KEYS[4], KEYS[4]);\n"
	    + "redis.call('lpush', KEYS[2], re);\n" + "return re;\n" + "end\n" + "end\n" + "return nil";
    static StopWatch watch = new StopWatch();

    public static void main(String[] args) throws InterruptedException {
	// testEval();
	// generateTestData();
	testTryGetHongBao();
    }

    static public void generateTestData() throws InterruptedException {
	Jedis jedis = new Jedis(host, 6379);
	jedis.flushAll();
	final CountDownLatch latch = new CountDownLatch(threadCount);
	for (int i = 0; i < threadCount; ++i) {
	    final int temp = i;
	    Thread thread = new Thread() {
		public void run() {
		    Jedis jedis = new Jedis(host);
		    int per = honBaoCount / threadCount;
		    Map object = new HashMap();
		    for (int j = temp * per; j < (temp + 1) * per; j++) {
			object.put("id", j);
			object.put("money", j);
			jedis.lpush(hongBaoList, JSON.toJSONString(object));
		    }
		    latch.countDown();
		}
	    };
	    thread.start();
	}
	latch.await();
    }

    static public void testTryGetHongBao() throws InterruptedException {
	final CountDownLatch latch = new CountDownLatch(threadCount);
	System.err.println("start:" + System.currentTimeMillis() / 1000);
	watch.start();
	for (int i = 0; i < threadCount; ++i) {
	    final int temp = i;
	    Thread thread = new Thread() {
		public void run() {
		    Jedis jedis = new Jedis(host);
		    String sha = jedis.scriptLoad(tryGetHongBaoScript);
		    System.out.println(sha);
		    int j = honBaoCount / threadCount * temp;
		    while (true) {
			Object object = jedis.eval(tryGetHongBaoScript, 4, hongBaoList, hongBaoConsumedList, hongBaoConsumedMap,
				"" + j);
			j++;
			if (object != null) {
			    // System.out.println("get hongBao:" + object);
			} else {
			    // 已经取完了
			    if (jedis.llen(hongBaoList) == 0)
				break;
			}
		    }
		    latch.countDown();
		}
	    };
	    thread.start();
	}

	latch.await();
	watch.stop();

	// System.err.println("time:" + watch.getTotalTimeSeconds());
	// System.err.println("speed:" + honBaoCount /
	// watch.getTotalTimeSeconds());
	System.err.println("end:" + System.currentTimeMillis() / 1000);
    }
}
