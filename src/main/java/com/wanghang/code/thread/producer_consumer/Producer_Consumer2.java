package com.wanghang.code.thread.producer_consumer;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *需求：一个初始值为0的变量，两个线程对其交替操作，一个加1，一个减1，来5轮
 *
 * 基于ReentrantLock实现生产者-消费者的模型
 *
 *
 *
 *
 */
class ShareData{
    private int number=0;

    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();

    /**
     * 一个线程加1
     */
    public void increment(){
        lock.lock();
         try {
             while (number!=0){
                 condition.await();
             }
             System.out.println(Thread.currentThread().getName()+"\t"+number);
             number++;
             condition.signalAll();
          }catch (Exception e){

          }finally {
            lock.unlock();
        }
    }

    /**
     *一个线程减1
     */
    public void decrement(){
        lock.lock();
         try {
             while (number==0){
                 condition.await();
             }
             System.out.println(Thread.currentThread().getName()+"\t"+number);
             number--;
             condition.signalAll();
         }catch (Exception e){

         }finally {
            lock.unlock();
         }
    }
}



public class Producer_Consumer2 {

    public static void main(String[] args) {
        ShareData shareData=new ShareData();

        new Thread(()->{
             for (int i = 0; i <5 ; i++) {
                 shareData.increment();
             }
        },"AA").start();

        new Thread(()->{
             for (int i = 0; i < 5; i++) {
                 shareData.decrement();
             }
        },"BB").start();
    }
}
