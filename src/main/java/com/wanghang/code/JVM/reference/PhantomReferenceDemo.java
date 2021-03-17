package com.wanghang.code.JVM.reference;


import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 *1)虚引用(PhantomReference)：
 *     a)虚引用又称为幽灵引用,与其他几种引用都不同，虚引用并不会决定对象的生命周期;
 *     b)如果一个对象持有虚引用，那么它就和没有任何引用一样，
 *       在任何时候都可能被垃圾回收器回收，它不能单独使用也不能通过它访问对象，虚引用必须和引用队列ReferenceQueue联合使用
 *
 *
 *2)虚拟引用的作用：
 *     虚引用的主要作用和跟踪对象被垃圾回收的状态，仅仅是提供一种确保对象被finalize以后，做某些事情的机制,这个就相当于Spring AOP里面的后置通知
 *
 *
 *
 *3)使用的场景：
 *    一般用于在回收时候做通知相关操作
 *
 *4)ReferenceQueue:引用队列
 *                 软引用，弱引用，虚引用在回收之前，需要在引用队列保存一下，在初始化的弱引用或者虚引用的时候，可以传入一个引用队列。
 *                 Object o1 = new Object();
 *                 // 创建引用队列
 *                 ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
 *                 // 创建一个弱引用
 *                 WeakReference<Object> weakReference = new WeakReference<>(o1, referenceQueue);
 *
 *
 *4)引用和引用队列一起使用的测试结果：
 *               a)虚引用里的对象在虚引用创建后,再获取引用里的对象获取不到;
 *               b)在初始化有引用队列的引用的的时候，从引用中获取对象弱引用和虚拟引用都获取不到对象，执行GC垃圾回收之后，引用里的对象都进入到了队列中;
 *
 */
public class PhantomReferenceDemo {

    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        // 创建弱引用队列
        ReferenceQueue<Object> weakReferenceQueue = new ReferenceQueue<>();
        // 创建一个弱引用
        WeakReference<Object> weakReference = new WeakReference<>(o1, weakReferenceQueue);


        //创建虚引用队列：
        ReferenceQueue<Object> phantomReferenceQueue = new ReferenceQueue<>();
        //创建一个虚引用
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1, phantomReferenceQueue);

        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(phantomReference.get());
        //引用队列中
        System.out.println(weakReferenceQueue.poll());
        System.out.println(phantomReferenceQueue.poll());

        o1 = null;
        System.gc();
        System.out.println("执行GC操作");

        TimeUnit.SECONDS.sleep(2);

        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(phantomReference.get());
        //队列中
        System.out.println(weakReferenceQueue.poll());
        System.out.println(phantomReferenceQueue.poll());
    }
}
