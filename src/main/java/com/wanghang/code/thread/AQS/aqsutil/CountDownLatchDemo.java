package com.wanghang.code.thread.AQS.aqsutil;



import java.util.concurrent.CountDownLatch;

/**
 *概念：
 *    让一些线程阻塞直到另一些线程完成一系列操作才被唤醒
 *    CountDownLatch主要有两个方法，当一个或多个线程调用await方法时，调用线程就会被阻塞，其它线程调用CountDown方法会将计数器减1（调用CountDown方法的线程不会被阻塞），当计数器的值变成零时，因调用await方法被阻塞的线程会被唤醒，继续执行。
 *
 * 场景：假设一个自习室里有7个人，其中有一个是班长，班长的主要职责就是在其它6个同学走了后，关灯，锁教室门，然后走人，班长是最后走的那一个，那么有什么方法能够控制班长这个线程是最后一个执行，而其它线程是随机执行的？
 *
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(6);

         for (int i = 1; i <=6 ; i++) {
             new Thread(()->{
                 System.out.println(Thread.currentThread().getName()+"\t"+"走了");
                 countDownLatch.countDown();
             },String.valueOf(i)).start();

         }

         //主线程(班长也要走了)
         countDownLatch.await();
         System.out.println("都走了，班长也要走了");
    }
}
