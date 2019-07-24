package com.ThreadTest;

import java.util.concurrent.ThreadPoolExecutor;

public class LockTest {
    static MyLock lock = new MyLock();
    static  int op = 0;
    public static void main(String[] args) {
        for (int i =  0; i < 30 ; i++) {
            new Thread(()->{
                set();
            }).start();
        }
    }
    private static void set(){
        //lock.lock();
       // try {
            System.out.println(++op);
        //}finally {
            //lock.unlock();
        //}
    }
}
