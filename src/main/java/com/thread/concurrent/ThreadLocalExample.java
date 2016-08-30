package com.thread.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 实现线程隔离功能
 * <code>ThreadLocal</code> 
 * 可实现AOP方法用法计算
 */
public class ThreadLocalExample {

    //如果调用get()时，没有用set()则会默认进行初始化,每个线程调用一次
    private static ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>(){
	protected Long initialValue(){
	    return System.currentTimeMillis();
	}
    };
    
    public static void begin(){
	TIME_THREADLOCAL.set(System.currentTimeMillis());
    }
    
    public static long end(){
	return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }
    
    public static void main(String[] args) {
	ThreadLocalExample.begin();
	try {
	    TimeUnit.SECONDS.sleep(2);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	System.out.println("Cost:"+ThreadLocalExample.end()+" mills");
    }
}
