package com;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){

        boolean op = true;
        assert op;
        System.out.println("true");
        assert !op;
        System.out.println("fales");
    }

}
