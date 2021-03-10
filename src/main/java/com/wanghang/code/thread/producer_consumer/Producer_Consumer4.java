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
 *https://www.cnblogs.com/xdyixia/p/9364247.html (关于synchronized实现锁原理以及JDK1.6以后的优化)
 *
 * https://www.bilibili.com/video/BV1xK41177rT?from=search&seid=8779262546108928963（B站关于synchronized的讲解）
 *
 * 3)相关优化：
 * synchronized关于锁的优化 https://blog.csdn.net/tongdanping/article/details/79647337
 *
 * synchronized加锁的集中状态：偏向锁态，轻量级锁态，重量级锁
 * 无锁：
 * 偏向锁态(偏向锁升级,偏向锁消除)：这个锁会偏向于第一个获得它的线程，大多数情况下不存在锁竞争，同一个线程获取同一把锁，如果同一个线程还要竞争获取锁代价很大所以引入了偏向锁;
 *      举例说明(偏向锁升级为轻量级的锁)：
 *      1)当线程1访问代码块并获取锁对象时，会在java对象头和栈帧中记录偏向的锁的threadID，因为偏向锁不会主动释放锁;
 *      2)因此以后线程1再次获取锁的时候，需要比较当前线程的threadID和Java对象头中的threadID是否一致，
 *        2.1)如果一致（还是线程1获取锁对象），则无需使用CAS来加锁、解锁;
 *        2.2)如果不一致（其他线程，如线程2要竞争锁对象，而偏向锁不会主动释放因此还是存储的线程1的threadID），那么需要查看Java对象头中记录的线程1是否存活，如果没有存活，那么锁对象被重置为无锁状态,
 *      3)如果线程1存活，那么立刻查找该线程（线程1）的栈帧信息，如果还是需要继续持有这个锁对象，那么暂停当前线程1，撤销偏向锁，升级为轻量级锁，如果线程1 不再使用该锁对象，那么将锁对象状态设为无锁状态，重新偏向新的线程
 *     偏向锁取消：
 *     偏向锁是默认开启的，而且开始时间一般是比应用程序启动慢几秒，如果不想有这个延迟，那么可以使用-XX:BiasedLockingStartUpDelay=0；
 *     如果不想要偏向锁，那么可以通过-XX:-UseBiasedLocking = false来设置；
 *
 * 轻量级锁态：轻量级锁考虑的是竞争锁对象的线程不多，而且线程持有锁的时间也不长的情景，
 *       举例说明(轻量级锁转成重量级锁:)
 *        1)线程1获取轻量级锁时会先把锁对象的对象头MarkWord复制一份到线程1的栈帧中创建的用于存储锁记录的空间(称为DisplacedMarkWord),然后使用CAS把对象头中的内容替换为线程1存储的锁记录（DisplacedMarkWord）的地址,
 *        2)如果在线程1复制对象头的同时（在线程1CAS之前），线程2也准备获取锁，复制了对象头到线程2的锁记录空间中，但是在线程2CAS的时候，发现线程1已经把对象头换了，线程2的CAS失败，那么线程2就尝试使用自旋锁来等待线程1释放锁,
 *        3)但是如果自旋的时间太长也不行，因为自旋是要消耗CPU的，因此自旋的次数是有限制的，比如10次或者100次，如果自旋次数到了线程1还没有释放锁，或者线程1还在执行，线程2还在自旋等待，这时又有一个线程3过来竞争这个锁对象，那么这个时候轻量级锁就会膨胀为重量级锁。重量级锁把除了拥有锁的线程都阻塞，防止CPU空转。
 * 重量级锁态：
 * 锁粗化：
 * 锁消除：
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
