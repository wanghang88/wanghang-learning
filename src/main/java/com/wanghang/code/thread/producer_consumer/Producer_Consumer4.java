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
 *                     虚拟机的对象头主要包括两部分数据：Mark Word（标记字段）、Klass Pointer（类型指针）
 *                     Klass Pointer：对象指向类元数据的指针,用来确定这个对象是是哪个类的实例。
 *                     Mark Word：用于存储对象自身的运行时数据，它是实现轻量级锁和偏向锁的关键。
 *实例数据(Instance Data):主要是存放类的数据信息，父类的信息，对象字段属性信息。
 *对齐填充(Padding):为了字节对齐，填充的数据，不是必须的。
 *
 * 2)Monitor:
 * 我们可以把它理解为一个同步工具，也可以描述为一种同步机制，它通常被描述为一个对象,基于Monitor实现的锁属于重量级锁,效率比较低下,
 * JDK1.6对锁的实现引入了大量的优化，如自旋锁、适应性自旋锁、锁消除、锁粗化、偏向锁、轻量级锁等技术来减少锁操作的开销
 *
 * 3)相关优化：
 * 自旋锁：
 * 适应自旋锁：
 * 锁消除：
 * 锁粗化：
 * 轻量级锁：
 * 偏向锁：
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
