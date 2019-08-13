package com.test;

public interface InterTest {
    static void stm(){
        System.out.println("ssss");
    }
    default void dd(){
        System.out.println("ddddd");
    }
    void hh();
}
