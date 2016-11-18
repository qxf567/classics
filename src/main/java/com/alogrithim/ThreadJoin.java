package com.alogrithim;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadJoin {

    public static AtomicInteger count = new AtomicInteger(1);

    public static void main(String[] args) {
	Thread t1 = new Thread(new T1(), "线程1");
	Thread t2 = new Thread(new T2(), "线程2");

	try {
	    // t1先启动
	    t1.start();
	    t1.join();
	    // t2
	    t2.start();
	    t2.join();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }
}

class T1 implements Runnable {

    public void run() {
	for (int i = 0; i < 5; i++) {
	    System.out.println(Thread.currentThread().getName() + ": " + i);
	    try {
		Thread.sleep(100);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }
}

class T2 implements Runnable {

    public void run() {
	for (int j = 5; j < 10; j++) {
	    System.out.println(Thread.currentThread().getName() + ": " + j);
	    try {
		Thread.sleep(100);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }
}
