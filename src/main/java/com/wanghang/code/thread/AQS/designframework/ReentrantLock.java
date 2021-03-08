package com.wanghang.code.thread.AQS.designframework;

import java.io.Serializable;


public class ReentrantLock  implements Lock,Serializable {

    private final Sync sync;

    public ReentrantLock() {
        sync = new NonfairSync();
    }

    //1抽象的Sync类继承AbstractQueuedSynchronizer定义lock方法
    abstract static class Sync extends AbstractQueuedSynchronizer{
        abstract void lock();


        final boolean nonfairTryAcquire(int acquires) {
            System.out.println("执行nonfairTryAcquire方法");
            return true;
        }

        protected final boolean tryRelease(int releases) {
            System.out.println("执行tryRelease方法");
            return true;
        }

        protected final boolean isHeldExclusively() {
            System.out.println("执行isHeldExclusively方法");
            return true;
        }
    }


    //NonfairSync继承Sync
    static final class NonfairSync extends Sync implements Serializable{
        @Override
        void lock() {
            System.out.println("NonfairSync继承Sync执行lock()方法");
        }

        // NonfairSync的tryAcquire方法：
        protected final boolean tryAcquire(int acquires) {
            return nonfairTryAcquire(acquires);
        }
    }

    //FairSync继承Sync
    static final class FairSync extends Sync{
        @Override
        void lock() {
            System.out.println("FairSync继承Sync执行lock()方法");
        }

        protected final boolean tryAcquire(int acquires) {
            System.out.println("FairSync执行tryAcquire()方法");
            return true;
        }
    }





    @Override
    public void lock() {
        sync.lock();
    }
    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.isHeldExclusively();
    }
    @Override
    public boolean tryLock() {
         return false;
        //return sync.nonfairTryAcquire(1);

    }
    @Override
    public void unlock() {
        sync.tryRelease(1);
    }


    /**
     * ReentrantLock
     *
     * 1:Lock接口定义锁的接口，有ReentrantLock实现锁相关的接口
     * 2:在AbstractQueuedSynchronizer抽象类中定义aqs的相关的方法tryAcquire(),tryRelease()等方法
     * 3:在ReentrantLock类中有抽象的类部类Sync继承AbstractQueuedSynchronizer,Sync并实现AbstractQueuedSynchronizer类中的方法;
     *    继承AbstractQueuedSynchronizer的子类必须实现其方法，不然会报错。
     *
     * 4:ReentrantLock中有个属性Sync这个类，并且构造函数有一个Sync
     *
     *
     */
    public static void main(String[] args) {
        ReentrantLock reentrantLock=new ReentrantLock();
        reentrantLock.unlock();
    }
}
