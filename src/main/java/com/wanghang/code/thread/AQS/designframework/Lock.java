package com.wanghang.code.thread.AQS.designframework;

public interface Lock {

    void lock();


    void lockInterruptibly() throws InterruptedException;


    boolean tryLock();


    void unlock();
}
