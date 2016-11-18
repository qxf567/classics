package com.lashou.redis.string;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import redis.clients.jedis.BinaryJedisCluster;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * 使用jedis提供的方法来操作redis-cluster 
 * 
 * 基于jedis-2.9.0
 * 
 */
public class RedisClusterTest {


    private static final int DEFAULT_TIMEOUT = 2000;
    private static final int DEFAULT_REDIRECTIONS = 5;
    
    public static void main(String[] args) {
	Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
	//Jedis Cluster will attempt to discover cluster nodes automatically
//	jedisClusterNodes.add(new HostAndPort("10.1.34.201", 7002));
	jedisClusterNodes.add(new HostAndPort("test.redis", 7001));
//	jedisClusterNodes.add(new HostAndPort("10.1.34.201", 7000));
//	jedisClusterNodes.add(new HostAndPort("test.redis", 7003));
//	jedisClusterNodes.add(new HostAndPort("test.redis", 7004));
//	jedisClusterNodes.add(new HostAndPort("test.redis", 7005));

	JedisPoolConfig config = new JedisPoolConfig();
//	config.setMaxTotal(50);
//	config.setMaxIdle(20);
//	config.setMaxWaitMillis(1000 * 1);
//	config.setTestOnBorrow(true);
//	config.setTestOnReturn(true);
	
	
	
	// public JedisCluster(Set<HostAndPort> jedisClusterNode, int connectionTimeout, int soTimeout,
        // int maxAttempts, String password, final GenericObjectPoolConfig poolConfig)
	//JedisCluster jc = new JedisCluster(jedisClusterNodes,DEFAULT_TIMEOUT, DEFAULT_TIMEOUT, DEFAULT_REDIRECTIONS,"123",config);
	JedisCluster jc = new JedisCluster(jedisClusterNodes);

	//字符串操作-----------------------
	jc.set("foo", "999999999999");
	String value = jc.get("foo");
	System.out.println(value);
	
	
	//hash操作-----------------------
	jc.hset("student_001", "name", "fei");
	jc.hset("student_001", "sex", "男");
	jc.hset("student_001", "score", "97");
	jc.hset("student_001", "class", "1-3");
	
	System.out.println(jc.hget("student_001", "sex"));
	System.out.println(jc.hget("student_001", "score"));
	System.out.println(jc.hget("student_001", "name"));
	
	
	
	Long hlen = jc.hlen("student_001");
	String type = jc.type("foo");
	System.out.println(hlen);
	System.out.println(type);
	
	System.out.println(jc.hgetAll("student_001"));
	System.out.println(jc.hgetAll("student_002"));
	System.out.println(jc.hvals("student_001"));
	
	//list操作-----------------------
	jc.lpush("languages", "javascript");
	jc.lpush("languages", "java");
	jc.lpush("languages", "java");
	jc.lpush("languages", "java");
	jc.lpush("languages", "python");
	jc.lpush("languages", "erlang");
	jc.rpush("languages", "php");
	
	System.out.println(jc.llen("languages"));
	System.out.println(jc.lindex("languages", 2));
	System.out.println(jc.lrange("languages", 0, 4));
	
	//set操作-----------------------
	jc.sadd("bbs", "tianya","discuz.net","google.com","tianya");
	
	System.out.println(jc.scard("bbs"));
	System.out.println(jc.smembers("bbs"));
	
	Map<String, Double> scoreMembers = new TreeMap<String,Double>();
	scoreMembers.put("google.com", new Double(10d));
	scoreMembers.put("baidu.com", new Double(9d));
	scoreMembers.put("bing.com", new Double(8d));
	
	//SortedSet操作-----------------------
	Long r = jc.zadd("page", 8, "baidu");
	
	System.out.println(r);
	
	System.out.println(jc.zrange("page", 0l, 10l));
	
	//有问题，不能用
	Long result = jc.zadd("rank ", scoreMembers);
	System.out.println(result);
	System.out.println(jc.zrange("rank", 0l, 10l));
	
	System.out.println(jc.zrange("page_rank", 0l, 10l));
	System.out.println(jc.zcard("page_rank"));
	System.out.println(jc.zcount("page_rank", "1", "9"));
	System.out.println(jc.zcount("page_rank", 3d, 19d));
	
	
	
//	 Map<String, JedisPool> clusterNodes = jc.getClusterNodes();
//	    Collection<JedisPool> values = clusterNodes.values();
//	    for(JedisPool jedisPool: values){
//	        Jedis jedis = jedisPool.getResource();
//	        try{
//	            System.out.println(jedis.clientGetname()+jedis.clusterInfo());
//	        }finally{
//	            jedis.close();
//	        }
//	    }
	
//	 node2 = new Jedis(nodeInfo2.getHost(), nodeInfo2.getPort());
//	    node2.auth("cluster");
	
	/*Jedis jedis = new Jedis("test.redis",7002);
	jedis.auth("123");
	jedis.set("foo", "bar33333333");
	String value2 = jedis.get("foo");
	System.out.println(value2);*/
	
//	    jc = new BinaryJedisCluster(jedisClusterNodes, DEFAULT_TIMEOUT, DEFAULT_TIMEOUT, 1, "123", DEFAULT_CONFIG);
//	    System.out.println(jc.get("fei88".getBytes()));
	    
	    
//	    System.out.println(node2.get("test"));
//	    
//	    JedisCluster jc2 = new JedisCluster(new HostAndPort("test.redis", 7000), DEFAULT_TIMEOUT, DEFAULT_TIMEOUT, DEFAULT_REDIRECTIONS, "123", DEFAULT_CONFIG);
//	    jc2.set("foo", "bar");
//	    jc2.set("test", "test");
//	    System.out.println(node3.get("foo"));
//	    System.out.println(node2.get("test"));
	
//	    JedisPool pool = new JedisPool("test.redis",6379);
//	    Jedis redis = pool.getResource();
//	    redis.set("fo3333333333o", "bar33333333");
//		String value2 = redis.get("fo3333333333o");
//		System.out.println(value2);
    }
    
}
