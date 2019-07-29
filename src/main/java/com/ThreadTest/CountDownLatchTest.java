package com.ThreadTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {
    static CountDownLatch countDownLatch = new CountDownLatch(2);
    public static void main(String[] args) throws InterruptedException {
        /*new Thread(()->{
            countDownLatch.countDown();
            System.out.println(2);
            countDownLatch.countDown();
            System.out.println(1);
        }).start();
        System.out.println("START======>");
        countDownLatch.await();
        System.out.println(0);*/
        new Thread(()->{
            countDownLatch.countDown();
            System.out.println("THRED 2===>"+2);
        }).start();
        new Thread(()->{
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            System.out.println("THRED 1===>"+1);

        }).start();
        System.out.println("START======>");
        countDownLatch.await(2L, TimeUnit.SECONDS);
        System.out.println("end "+ 0);
    }
}
