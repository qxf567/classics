package com.lashou.redis.string;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands.GeoLocation;
import org.springframework.data.redis.core.ClusterOperations;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.HyperLogLogOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;

/**
 * spring-date-redis 1.8
 *  操作redis集群
 * 
 */
public class SpringRedisCluster {

    public static <V> void main(String[] args) {

	ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:com/redis/redis.xml");
	RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>) ac.getBean("redisTemplate");

	redisTemplate.opsForValue().set("foo", "999999999");
	System.out.println(redisTemplate.opsForValue().get("foo"));

	// hset
	redisTemplate.opsForHash().put("website", "google", "www.google.com.hk");
	redisTemplate.opsForHash().put("website", "baidu", "www.baidu.com");

	// hget
	Object object = redisTemplate.opsForHash().get("website", "baidu");
	System.out.println(object);
	// hlen
	System.out.println(redisTemplate.opsForHash().size("website"));
	// hvals
	System.out.println(redisTemplate.opsForHash().values("website"));
	// keys
	System.out.println(redisTemplate.opsForHash().keys("website"));
	// HEXISTS
	System.out.println(redisTemplate.opsForHash().hasKey("website", "baidu1"));

	// list 操作
	ListOperations<String, Object> listOper = redisTemplate.opsForList();
	// lpush
	listOper.leftPush("languages", "python");
	listOper.leftPush("languages", "javascript");
	listOper.leftPush("languages", "erlang");
	listOper.leftPush("languages", "ruby");
	listOper.leftPush("languages", "java");

	// lindex
	System.out.println(listOper.index("languages", 0));
	// llen
	System.out.println(listOper.size("languages"));
	System.out.println(listOper.range("languages", 0, 5));

	SetOperations<String, Object> setOpe = redisTemplate.opsForSet();

	setOpe.add("bbs2", "discuz", "tianya", "baidu", "tianya");

	System.out.println(setOpe.size("bbs2"));

	// SRANDMEMBER
	System.out.println(setOpe.randomMember("bbs2"));
	System.out.println(setOpe.randomMember("bbs2"));

	System.out.println(setOpe.members("bbs2"));

	ZSetOperations<String, Object> zSetOpe = redisTemplate.opsForZSet();

	zSetOpe.add("rank", "baidu", 10);
	zSetOpe.add("rank", "g", 11);
	zSetOpe.add("rank", "360", 9);
	zSetOpe.add("rank", "bing", 7);

	System.out.println(zSetOpe.range("rank", 0, 25));
	System.out.println(zSetOpe.reverseRange("rank", 0, 25));

	HyperLogLogOperations<String, Object> hyperOpe = redisTemplate.opsForHyperLogLog();

	hyperOpe.add("runoobkey", "redis");
	hyperOpe.add("runoobkey", "mongodb");
	hyperOpe.add("runoobkey", "mongodb");
	hyperOpe.add("runoobkey", "mysql");
	System.out.println(hyperOpe.size("runoobkey"));

	ClusterOperations<String, Object> clusterOpe = redisTemplate.opsForCluster();

	GeoOperations<String, Object> geoOpe = redisTemplate.opsForGeo();
	Point point = new Point(116.335, 39.255);
	GeoLocation<Object> location = new GeoLocation<Object>("point1", point);

	Point point2 = new Point(116.335, 39.3);
	GeoLocation<Object> location2 = new GeoLocation<Object>("point3", point2);

	System.out.println(geoOpe.geoAdd("geo01", location));

	System.out.println(geoOpe.geoAdd("geo01", location2));

	System.out.println(geoOpe.geoDist("geo01", "point1", "point3"));

	System.out.println(redisTemplate.randomKey());
    }
}
