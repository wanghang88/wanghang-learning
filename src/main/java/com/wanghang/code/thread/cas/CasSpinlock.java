package com.wanghang.code.thread.cas;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁：
 *      是指尝试获取锁的线程不会立即阻塞，而是采用循环的方式去尝试获取锁，这样的好处是减少线程上下文切换的消耗，缺点是循环会消耗CPU
 *      原来提到的比较并交换，底层使用的就是自旋，自旋就是多次尝试，多次访问，不会阻塞的状态就是自旋
 *
 *基于AtomicReference实现CAS自旋锁:
 *      场景：A线程先进来调用myLock方法自己持有锁5秒，B随后进来发现当前有线程持有锁，不是null，所以只能通过自旋等待，直到A释放锁后B随后抢到
 *
 *2:基于csa原理的分析(总结得不错，以及还有对不安全集合变成安全集合的总结)
   https://www.jianshu.com/p/5d0a64ae1a6b
 *
 *
 */
public class CasSpinlock {

    // 现在的泛型装的是Thread，原子引用线程
    private AtomicReference<Thread> atomicReference=new AtomicReference<>();



    //lock:atomicReference引用为null时,将当前线程设置为当前引用;
    public void lock(){
        Thread currentThread = Thread.currentThread();
        System.out.println(currentThread.getName()+"\t"+"线程 come in");

        // 开始自旋，期望值是null，更新值是当前线程，如果是null，则更新为当前线程，否者自旋
        while (!atomicReference.compareAndSet(null,currentThread)){

        }
    }

    //unlock:相当于自己用完锁后将atomicReference的引用设置成null;
    public void unlock(){
        Thread currentThread = Thread.currentThread();
        atomicReference.compareAndSet(currentThread,null);

        System.out.println(currentThread.getName()+"\t 释放锁");
    }




    public static void main(String[] args) throws InterruptedException {

        CasSpinlock casSpinlock=new CasSpinlock();

        new Thread(()->{
            //1:线程获取锁
            casSpinlock.lock();
            long start= System.currentTimeMillis();

            //2：执行5秒钟
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {

            }

            //3:t1线程释放锁
            casSpinlock.unlock();
        },"t1").start();

        //main线程休眠2秒钟：
        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            //获取锁
            casSpinlock.lock();

            //解锁
            casSpinlock.unlock();

        },"t2").start();

    }
}
