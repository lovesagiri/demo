package com.ThreadTest;

import java.util.Map;
import java.util.concurrent.*;

public class CyclicBarrierExmple implements Runnable {
    //四个屏障
    private  CyclicBarrier cb4 = new CyclicBarrier(4,this);
    //线程池
    private  Executor ex = Executors.newFixedThreadPool(4);
    //缓存集合
    private  ConcurrentHashMap<String,Integer> cm = new ConcurrentHashMap();
    public static void main(String[] args) {
       new CyclicBarrierExmple().conut();
    }

    private  void conut() {
            for (int i = 0; i <4 ; i++) {
                System.out.println("thread===>" + i);
                ex.execute(new Runnable() {
                    @Override
                    public void run() {
                        cm.put(Thread.currentThread().getName(),1);
                        try {
                            cb4.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
    }

    @Override
    public void run() {
        int result = 0;
        for (Map.Entry<String,Integer> entry : cm.entrySet()) {
            result +=entry.getValue();
        }
        System.out.println("等待线程===》" + cb4.getNumberWaiting());
        System.out.println(result);
    }
}
