package com.ThreadTest;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {
    static final Exchanger<String> exchanger = new Exchanger();
    static ExecutorService es = Executors.newFixedThreadPool(2);
    public static void main(String[] args) {
        es.execute(()->{
            String str1= "窗前明月光";
            try {
                System.out.println("A======>" + str1 + "," + exchanger.exchange(str1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        es.execute(()->{
            String str2 = "疑是地上霜";
            try {
                System.out.println("B======>" + exchanger.exchange(str2)+ "," + str2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        es.shutdown();
    }
}
