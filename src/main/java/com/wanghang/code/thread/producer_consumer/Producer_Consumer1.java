package com.wanghang.code.thread.producer_consumer;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于RentrantLock实现生产者和消费者的案例
 *
 *多线程之间按顺序调用，实现 A-> B -> C 三个线程启动，要求如下：
 * AA打印5次，BB打印10次，CC打印15次，紧接着 AA打印5次，BB打印10次，CC打印15次
 * 一共10轮
 */
class MyData {
    //A线程为1的时候执行，B线程为2的时候执行，C线程为3的时候执行
    private volatile int num=1;

    private Lock lock=new ReentrantLock();
    private Condition c1=lock.newCondition();
    private Condition c2=lock.newCondition();
    private Condition c3=lock.newCondition();

    //线程1干活
    public void printA(){
        lock.lock();
        try {
            //1,判断线程等待:AA线程不等于1的时候就等待
            while (num!=1){
                c1.await();
            }
            //2干活,AA线程开始干活
            for (int i=1;i<=5;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+num+"\t"+i);
            }
            //3通知其他线程，
            num=2;
            c2.signal();
        }catch (Exception e){


        }finally {
            lock.unlock();
        }
    }

    //线程2开始干活
    public void printB(){
        lock.lock();
        try {
            //1,判断线程等待:AA线程不等于1的时候就等待
            while (num!=2){
                c2.await();
            }
            //2干活,AA线程开始干活
            for (int i=1;i<=10;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+num+"\t"+i);
            }
            //3通知其他线程，
            num=3;
            c3.signal();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    //线程3开始干活
    public void printC(){
        lock.lock();
        try {
            //1,判断线程等待:AA线程不等于1的时候就等待
            while (num!=3){
                c3.await();
            }
            //2干活,AA线程开始干活
            for (int i=1;i<=15;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+num+"\t"+i);
            }
            //3通知其他线程，
            num=1;
            c1.signal();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }
}


public class Producer_Consumer1 {

    public static void main(String[] args) {
        MyData myData = new MyData();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                myData.printA();
            }
        }, "t1").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                myData.printB();
            }
        }, "t2").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                myData.printC();
            }
        }, "t3").start();
    }

}
