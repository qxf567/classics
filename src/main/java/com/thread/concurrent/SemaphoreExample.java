package com.thread.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量
 * A counting semaphore. Conceptually, a semaphore maintains a set of permits
 * <code>Semaphore</code> ，有时被称为信号灯，是在多线程环境下使用的一种设施, 它负责协调各个线程,
 * 以保证它们能够正确、合理的使用公共资源。
 * 
 * 一个计数信号量。从概念上讲，信号量维护了一个许可集。如有必要，在许可可用前会阻塞每一个 acquire()，然后再获取该许可。每个 release()
 * 添加一个许可，从而可能释放一个正在阻塞的获取者。但是，不使用实际的许可对象，Semaphore
 * 只对可用许可的号码进行计数，并采取相应的行动。拿到信号量的线程可以进入代码，否则就等待。通过acquire()和release()获取和释放访问许可。
 */
public class SemaphoreExample {

    public static void main(String[] args) {
	// 线程池
	ExecutorService exec = Executors.newCachedThreadPool();
	// 只能5个线程同时访问
	final Semaphore semp = new Semaphore(5);
	// 模拟50个客户端访问
	for (int index = 0; index < 50; index++) {
	    final int NO = index;
	    Runnable run = new Runnable() {
		public void run() {
		    try {
			// 获取许可
			semp.acquire();
			System.out.println("Accessing: " + NO);
			Thread.sleep((long) (Math.random() * 6000));
			// 访问完后，释放
			semp.release();
			// availablePermits()指的是当前信号灯库中有多少个可以被使用
			System.out.println("-----------------" + semp.availablePermits());
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
	    };
	    exec.execute(run);
	}
	// 退出线程池
	exec.shutdown();
    }
}
