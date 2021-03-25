package com.wanghang.code.thread.AQS.aqsutil;


import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 *概念:
 *    信号量主要用于两个目的,一个是用于共享资源的互斥使用,另一个用于并发线程数的控制
 *    一个资源有多个副本可供同时使用，比如打印机房有多个打印机,Java提供了另外的并发访问控制--资源的多副本的并发访问控制Semaphore
 *    Semaphore是一种基于计数的信号量,它可以设定一个阈值,多个线程竞争获取许可信号,做完自己的事情后归还，超过阈值后，线程申请许可信号将会被阻塞。
 *    比如数据库连接池，我们也可以创建计数为1的Semaphore，将其作为一种类似互斥锁的机制(信号量为1的情况可以实现类似于互斥锁的机制)
 *
 * 场景：
 *     我们模拟一个抢车位的场景，假设一共有6个车，3个停车位(车位满了之后，后面的三辆车则在外面等，车库有车离开则再放车进去，也就是离开一辆则放进一辆)
 */

public class SemaphoreDemo {

    public static void main(String[] args) {
        //定义3个信号量,代表三个车位，默认使用非公平锁：
        Semaphore semaphore=new Semaphore(3,false);

         for(int i=1;i<=6;i++){
             new Thread(()->{
                 try {
                     //1:抢车位
                     semaphore.acquire();
                     System.out.println(Thread.currentThread().getName()+"\t"+"抢到车位");

                     //2:停车3秒
                     try {
                         TimeUnit.SECONDS.sleep(3);
                     } catch (InterruptedException e) {

                     }

                     //3:停车3秒之后离开
                     System.out.println(Thread.currentThread().getName()+"\t"+"离开");

                 } catch (InterruptedException e) {

                 }finally {
                     //4:离开后就释放了车位
                     semaphore.release();
                 }
             },String.valueOf(i)).start();
         }
    }
}
