package com.thread.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <code>CountDownLatch</code> (倒计时门闩)
 * 
 * 一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。用给定的计数 初始化 CountDownLatch。由于调用了
 * countDown() 方法，所以在当前计数到达零之前，await 方法会一直受阻塞。之后，会释放所有等待的线程，await
 * 的所有后续调用都将立即返回。这种现象只出现一次——计数无法被重置。 一个线程(或者多个)， 等待另外N个线程完成某个事情之后才能执行
 * 
 * 
 */
public class CountDownLatchExample {

    public static void main(String[] args) {
	CountDownLatchExample.useTwo();
    }

    public void useOne() {
	final CountDownLatch doneSignal = new CountDownLatch(10);

	ExecutorService executor = Executors.newFixedThreadPool(10);
	for (int i = 0; i < 10; i++) {
	    executor.submit(new Runnable() {

		@Override
		public void run() {
		    try {
			// 如果当前计数为零，则此方法立即返回。
			// 等待
			Thread.sleep((long) (Math.random() * 10000));
			System.out.println(Thread.currentThread().getName() + " arrived");
			// 每个选手到达终点时，end就减一
			doneSignal.countDown();
		    } catch (InterruptedException e) {
		    }
		}
	    });
	}

	try {
	    doneSignal.await();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	System.out.println("---------ok");
	executor.shutdown();
    }

    public static void useTwo() {
	// 开始的倒数锁
	final CountDownLatch start = new CountDownLatch(1);

	// 结束的倒数锁
	final CountDownLatch end = new CountDownLatch(10);

	// 十名选手
	final ExecutorService exec = Executors.newFixedThreadPool(10);

	for (int index = 0; index < 10; index++) {
	    final int NO = index + 1;
	    Runnable run = new Runnable() {
		public void run() {
		    try {
			// 如果当前计数为零，则此方法立即返回。
			// 等待
			start.await();
			Thread.sleep((long) (Math.random() * 10000));
			System.out.println("No." + NO + " arrived ...");
		    } catch (InterruptedException e) {
		    } finally {
			// 每个选手到达终点时，end就减一
			end.countDown();
		    }
		}
	    };
	    exec.submit(run);
	}

	// 等待end变为0，即所有选手到达终点
	try {
	    System.out.println();
	    start.countDown();
	    System.out.println("Game Start------");
	    Thread.sleep((long) (Math.random() * 10000));
	    end.await();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	} finally {
	}
	System.out.println("Game Over-----");

	exec.shutdown();
    }
}
