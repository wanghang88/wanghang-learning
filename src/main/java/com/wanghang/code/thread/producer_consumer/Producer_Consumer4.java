package com.wanghang.code.thread.producer_consumer;


/**
 *最原始基于synchronized和wait()和notify()实现生产者和消费者模型的代码;
 *
 *synchronized实现锁的原理：
 *  synchronized的底层实现是完全依赖与JVM虚拟机以及Java对象头，以及Monitor对象监视器
 *1)java对象头：
 * 可以参考对象在java堆中的存储：https://www.cnblogs.com/jajian/p/13681781.html
 *
 *JVM虚拟机中，对象在内存中的存储布局，可以分为三个区域，
 *对象头(object Header):包括了关于堆对象的布局、类型、GC状态、同步状态和标识哈希码的基本信息。Java对象和jvm内部对象都有一个共同的对象头格式
 *实例数据(Instance Data):主要是存放类的数据信息，父类的信息，对象字段属性信息。
 *对齐填充(Padding):为了字节对齐，填充的数据，不是必须的。
 *
 *
 *
 *
 *
 */



class ShareData4{
    private int numb=0;

    private Object objectLock=new Object();
    public void increment() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"\t"+"come in ");

        synchronized(objectLock) {
            while (numb != 0) {
                objectLock.wait();
            }
            numb++;
            System.out.println(Thread.currentThread().getName()+"\t"+"numb当前的值为:"+numb);
            objectLock.notify();
        }
    }
    public void decrement() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"\t"+"come in ");

        synchronized (objectLock) {
            while (numb == 0) {
                objectLock.wait();
            }
            numb--;
            System.out.println(Thread.currentThread().getName()+"\t"+"numb当前的值为:"+numb);
            objectLock.notify();
        }
    }

 /*   public synchronized void increment() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"\t"+"come in");
            while (numb != 0) {
                this.wait();
            }
            numb++;
            System.out.println(Thread.currentThread().getName()+"\t"+"numb当前的值为:"+numb);
            this.notifyAll();
    }
    public synchronized void decrement() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"\t"+"come in ");
            while (numb == 0) {
                this.wait();
            }
            numb--;
            System.out.println(Thread.currentThread().getName()+"\t"+"numb当前的值为:"+numb);
            this.notifyAll();
    }*/

}


public class Producer_Consumer4 {
    public static void main(String[] args) {
        ShareData4 shareData4=new ShareData4();
        new Thread(()->{
            try {
                 for (int i = 1; i <=5 ; i++) {
                     shareData4.increment();
                 }
            } catch (InterruptedException e) {

            }
        },"AA").start();

        new Thread(()->{
            try {
                 for (int i = 1; i <=5 ; i++) {
                     shareData4.decrement();
                 }
            } catch (InterruptedException e) {

            }
        },"BB").start();
    }
}
