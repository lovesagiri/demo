package com;

import java.util.concurrent.TimeUnit;

/*
  * @Description: todo
  * @Author: yangzhengyang
  * @Date: 2019/7/17 9:34
  */
public class App
{
    public static void main( String[] args ) throws Exception{
        //0 1 1 2 3 5 8 ..
        int x = 5;
        int fi = fib(x);
        fibp(x);
        System.out.println("fi======>" + fi);

    }

    private static void fibp(int x) {
        for (int i = 1; i <= x; i++) {;
            System.out.println(fib(i));
        }
    }

    private static int  fib(int x) {
        int temp = -1;
        if(x<3){
            if(x==1)
               temp = 0;
            if(x == 2)
                temp = 1;
        }else{
            temp = fib(x-1)+fib(x-2);
        }
        return temp;

    }
}
