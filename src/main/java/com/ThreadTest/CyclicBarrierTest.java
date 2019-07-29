package com.ThreadTest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    /*static CyclicBarrier cb = new CyclicBarrier(2);
    public static void main(String[] args) {
        new Thread(()->{
            try {
                System.out.println("THRED child===>start");
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("THRED child===>end");
        }).start();
        try {
            System.out.println("main start");
            cb.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("可以执行拉 end");
    }*/

    static CyclicBarrier cb = new CyclicBarrier(2,new RunA());
    public static void main(String[] args) {
        new Thread(()->{
            try {
                System.out.println("THRED child===>start");
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("THRED child===>end");
        }).start();
        try {
            System.out.println("main start");
            cb.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("可以执行拉 end");
    }

    private static class RunA implements Runnable {
        @Override
        public void run() {
            System.out.println("runa=====>");
        }
    }
}
