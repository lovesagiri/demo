package com.fan;

public class TestF {

    public static void main(String[] args) {
        TestF.printMsg("111",222,"aaaa","2323.4",new GenTest<String>());
    }
    public static  <T> void printMsg( T... args){
        for(T t : args){
            System.out.println(("泛型测试"+"t is " + t));
        }
    }
}
