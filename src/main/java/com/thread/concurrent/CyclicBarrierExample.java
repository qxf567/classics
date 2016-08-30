package com.thread.concurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <code>CyclicBarrier</code>(循环栅栏) 
 * A synchronization aid that allows a set of
 * threads to all wait for each other to reach a common barrier point.
 *  一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)  
 * */
public class CyclicBarrierExample {

    public static void main(String[] args) {
	 //如果将参数改为4，但是下面只加入了3个选手，这永远等待下去  
        //Waits until all parties have invoked await on this barrier.   
        CyclicBarrier barrier = new CyclicBarrier(3);  
  
        ExecutorService executor = Executors.newFixedThreadPool(3);  
        executor.submit(new Thread(new Runner(barrier, "1号选手")));  
        executor.submit(new Thread(new Runner(barrier, "2号选手")));  
        executor.submit(new Thread(new Runner(barrier, "3号选手")));  
  
        executor.shutdown();  
     
        barrier.reset();
        
        System.out.println("----------------------------------------------");
        ExecutorService executor2 = Executors.newFixedThreadPool(3);  
        executor2.submit(new Thread(new Runner(barrier, "11号选手")));  
        executor2.submit(new Thread(new Runner(barrier, "21号选手")));  
        executor2.submit(new Thread(new Runner(barrier, "31号选手")));  
  
        executor2.shutdown(); 
    }
}



class Runner implements Runnable {  
    private CyclicBarrier barrier;  
  
    private String name;  
  
    public Runner(CyclicBarrier barrier, String name) {  
        super();  
        this.barrier = barrier;  
        this.name = name;  
    }  
  
    @Override  
    public void run() {  
        try {  
            Thread.sleep(1000 * (new Random()).nextInt(8));  
            System.out.println(name + " 准备好了...");  
            // barrier的await方法，在所有参与者都已经在此 barrier 上调用 await 方法之前，将一直等待。  
            barrier.await();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        } catch (BrokenBarrierException e) {  
            e.printStackTrace();  
        }  
        System.out.println(name + " 起跑！");  
    }  
}  