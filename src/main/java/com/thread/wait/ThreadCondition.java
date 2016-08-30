package com.thread.wait;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程交替输出内容
 * 
 * 使用<code>Lock</code>, <code>Condition</code>
 * */
public class ThreadCondition {

    private Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    int count = 1;

    public void one() {
	while (count < 100) {
	    lock.lock();
	    try {
		if (count % 2 == 1) {
		    System.out.println(Thread.currentThread().getName() + " : " + count++);
		    condition.signalAll();
		} else {
		    try {
			condition.await();
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
	    } finally {
		lock.unlock();
	    }
	}
    }

    public void two() {
	while (count < 100) {
	    lock.lock();
	    try {
		if (count % 2 == 0) {
		    System.out.println(Thread.currentThread().getName() + " : " + count++);
		    condition.signalAll();
		} else {
		    try {
			condition.await();
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
	    } finally {
		lock.unlock();
	    }
	}
    }

    public static void main(String[] args) {
	final ThreadCondition tc = new ThreadCondition();
	Thread one = new Thread(new Runnable() {
	    @Override
	    public void run() {
		tc.one();
	    }
	});

	Thread two = new Thread(new Runnable() {
	    @Override
	    public void run() {
		tc.two();
	    }
	});

	one.start();
	two.start();
    }
}
