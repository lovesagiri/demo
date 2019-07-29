package com.ThreadTest;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {
    /*static Object lock = new Object();
    static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {

       new Thread(()->{
           synchronized (lock){
               while (flag){
                   System.out.println("waiting start=====>");
                   try {
                       lock.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }
       },"watirng").start();

        TimeUnit.SECONDS.sleep(10L);
        new Thread(()->{
            synchronized (lock){
                lock.notifyAll();
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("notify 起床--------");
            }
        },"notifying").start();
    }*/
    private Executor ex = Executors.newFixedThreadPool(2);
    public static void main(String[] args) {
        //testRun();
        new Test().testRun();

    }

    private  void testRun() {
        ex.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("eeeee");
            }
        });
    }
}
