package com.thread.wait;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer {
    // 可重入的互斥锁
    final Lock lock = new ReentrantLock();
    // 表示"缓冲区没满"条件
    final Condition notFull = lock.newCondition();
    // 表示"缓冲区非空"条件
    final Condition notEmpty = lock.newCondition();
    // 存储空间
    // final Object[] items = new Object[100];
    final ArrayList<Object> list = new ArrayList<Object>();
    final Queue<Object> queue = new LinkedList<Object>();
    final int maxSize = 10;
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {

	lock.lock();
	try {
	    while (count == maxSize)
		notFull.await();
	    queue.offer(x);
	    ++count;

	    notEmpty.signal();
	} finally {
	    lock.unlock();
	}
    }

    public Object take() throws InterruptedException {
	lock.lock();
	try {
	    while (count == 0)
		notEmpty.await();
	    Object x = queue.poll();
	    --count;
	    notFull.signal();
	    return x;
	} finally {
	    lock.unlock();
	}
    }

    public static void main(String[] args) {
	int threadCount = 5;

	BoundedBuffer b = new BoundedBuffer();

	for (int i = 0; i < threadCount; i++) {
	    if (i % 2 == 0)
		new PutThread(b).start();
	    new TakeThread(b).start();
	}

    }

    public static class PutThread extends Thread {
	private final BoundedBuffer b;
	private int put = 0;

	public PutThread(BoundedBuffer b) {
	    this.b = b;
	}

	@Override
	public void run() {
	    try {
		String data = null;
		while (true) {
		    data = "data" + (++put);
		    System.out.println(data);
		    b.put(data);
		    Thread.sleep(100);
		}
	    } catch (InterruptedException e) {
		// e.printStackTrace();
		Thread.currentThread().interrupt();
	    }
	}
    }

    public static class TakeThread extends Thread {
	private final BoundedBuffer b;

	public TakeThread(BoundedBuffer b) {
	    this.b = b;
	}

	@Override
	public void run() {
	    try {
		while (true) {
		    System.out.println(b.take());
		}
	    } catch (InterruptedException e) {
		// e.printStackTrace();
		Thread.currentThread().interrupt();
	    }
	}
    }
}
