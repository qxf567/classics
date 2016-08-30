package com.thread.wait;

import java.util.LinkedList;
import java.util.List;

/**
 * 阻塞队列
 * 
 * <code>Semaphore</code>
 * <code>ArrayBlockingQueue</code>
 * <code>LinkedBlockingQueue</code>
 */
public class BlockingQueue {

    private List<Object> queue = new LinkedList<Object>();
    private int limit = 10;

    public synchronized void enqueue(Object item) throws InterruptedException {
	while (limit == queue.size()) {
	    wait();
	}

	if (this.queue.size() == 0) {
	    notifyAll();
	}
	queue.add(item);
    }

    public synchronized Object dequeue() throws InterruptedException {
	Object o = null;
	while (queue.size() == 0) {
	    wait();
	}
	if (this.queue.size() == this.limit) {
	    notifyAll();
	}
	o = queue.remove(0);
	return o;
    }
}
