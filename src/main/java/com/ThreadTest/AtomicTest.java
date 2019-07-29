package com.ThreadTest;

import com.msgpack.User;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicTest {
    //static AtomicInteger ai = new AtomicInteger(2);
    //static AtomicReference<UserT> af = new AtomicReference<UserT>();
    static AtomicIntegerFieldUpdater<UserT> aif = AtomicIntegerFieldUpdater.newUpdater(UserT.class,"old");
    public static void main(String[] args) {
       /* System.out.println(ai.getAndIncrement());
        System.out.println(ai.get());
        System.out.println(ai.getAndSet(7));
        System.out.println(ai.get());*/
       /*UserT tt = new UserT("sagirl",12);
       af.set(tt);
        System.out.println(af.get().getName());
        System.out.println(af.get().getOld());
       UserT tup = new UserT("wadaxi",15);
       af.compareAndSet(tt,tup);
        System.out.println(af.get().getName());
        System.out.println(af.get().getOld());*/
       UserT uu = new UserT("柯南",11);
       System.out.println( aif.getAndIncrement(uu));
        System.out.println(aif.get(uu));


    }

    public static class UserT {
        private String name;

        public volatile int old;

        public UserT(String name,int old){
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public int getOld() {
            return old;
        }

    }
}
