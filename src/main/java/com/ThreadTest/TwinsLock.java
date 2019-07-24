package com.ThreadTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class TwinsLock implements Lock {

    private final Sync sync =  new Sync(2);

    private final static class Sync extends AbstractQueuedSynchronizer {

        public Sync(int count) {
            setState(2);
        }

        @Override
        public int tryAcquireShared(int reduce) {
            while (true){
                int current = getState();
                int newcount = current - reduce;
                if(newcount < 0 || compareAndSetState(current,newcount)){
                    return newcount;
                }
            }
        }

        @Override
        public boolean tryReleaseShared(int arg) {
            while (true){
                int current = getState();
                int newCount = current + arg;
                if (compareAndSetState(current,newCount))
                    return true;
            }
        }

        final ConditionObject newCondition() {
            return new ConditionObject();
        }
    }
    @Override
    public void lock() {
        sync.acquireShared(1);
    }
    @Override
    public void unlock() {
        sync.releaseShared(1);

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }


    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
