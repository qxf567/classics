package com.thread.wait;

/**
 * 多线程交替输出内容
 * 
 * 使用wait(),notify(),notifyAll()
 * */
public class ThreadWait {

    public static void main(String[] args) {
	Num num = new Num();
	Thread one = new Thread(new ThreadOne(num));
	Thread two = new Thread(new ThreadTwo(num));
	one.start();
	two.start();
    }

}

class Num {
    public int count = 1;
}

class ThreadOne implements Runnable {

    private Num number;

    public ThreadOne(Num num) {
	this.number = num;
    }

    @Override
    public void run() {
	while (number.count < 100) {
	    synchronized (number) {
		if (number.count % 2 == 1) {
		    System.out.println(Thread.currentThread().getName() + " : " + number.count++);
		    number.notifyAll();
		} else {
		    try {
			number.wait();
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
	    }
	}
    }
}

class ThreadTwo implements Runnable {

    private Num number;

    public ThreadTwo(Num num) {
	this.number = num;
    }

    @Override
    public void run() {
	while (number.count < 100) {
	    synchronized (number) {
		if (number.count % 2 == 0) {
		    System.out.println(Thread.currentThread().getName() + " : " + number.count++);
		    number.notifyAll();
		} else {
		    try {
			number.wait();
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
	    }
	}
    }
}