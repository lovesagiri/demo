package com.ThreadTest;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    private static final int COUNT = 10;
    static ExecutorService ex = Executors.newFixedThreadPool(COUNT);
    static Semaphore semaphore = new Semaphore(5);
    public static void main(String[] args) {
        for (int i = 0; i < COUNT; i++) {
            ex.execute(()->{
                try {
                    semaphore.acquire();
                    System.out.println("save data ======>");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            });
        }
        ex.shutdown();
    }
}
