package com.wanghang.code.thread.threadlocal;


import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal的理解：
 *
 * 1):原理理解：
 * https://www.jianshu.com/p/6fc3bba12f38
 *  a)通过ThreadLocal包装共享资源解决多线程安全问题
 * ThreadLocal是解决线程安全问题一个很好的思路，它通过为每个线程提供一个独立的变量副本解决了变量并发访问的冲突问题，
 *            在很多情况下，ThreadLocal比直接使用synchronized同步机制解决线程安全问题更简单，更方便，且结果程序拥有更高的并发性。
              为保证多个线程对共享变量的安全访问，通常会使用synchronized来保证同一时刻只有一个线程对共享变量进行操作。
                     这种情况下可以将类变量放到ThreadLocal类型的对象中，使变量在每个线程中都有独立拷贝。
  b)原理：
       在每个线程Thread内部有一个ThreadLocal.ThreadLocalMap类型的成员变量threadLocals，这个threadLocals就是用来存储实际的变量副本的
       key值为当前ThreadLocal变量，value为变量副本（即T类型的变量）
       初始时，在Thread里面，threadLocals为空，当通过ThreadLocal变量调用get()方法或者set()方法，就会对Thread类中的threadLocals进行初始化;
       并且以当前ThreadLocal变量为键值，以ThreadLocal要保存的副本变量为value，存到threadLocals。 然后在当前线程里面，如果要使用副本变量，就可以通过get方法在threadLocals里面查找.

 *
 *2)ThreadLocal的常见的使用场景：
 *                          使用ThreadLocal使用场景为用来解决数据库连接、Session管理等
 *
 *
 *
 *3)ThreadLocal产生内存泄漏原因分析:
 *3.1:
 * 引用：
 * Object o = new Object(),这个o，我们可以称之为对象引用，而new Object()我们可以称之为在内存中产生了一个对象实例,当写下 o=null时，只是表示o不再指向堆中object的对象实例，不代表这个对象实例不存在了.
 * 强引用,一直活着：类似“Object obj=new Object（）”这类的引用，只要强引用还存在，垃圾收集器永远不会回收掉被引用的对象实例
 *
 * 软引用,有一次活的机会:软引用关联着的对象,在系统将要发生内存溢出异常之前，将会把这些对象实例列进回收范围之中进行第二次回收。如果这次回收还没有足够的内存，才会抛出内存溢出异常
 *       在JDK 1.2之后，提供了SoftReference类来实现软引用.
 * 弱引用,回收就会死亡,当垃圾收集器工作时，无论当前内存是否足够，都会回收掉只被弱引用关联的对象实例,
 *       在JDK 1.2之后，提供了WeakReference类来实现弱引用.
 * 虚引用,它是最弱的一种引用关系,对象的虚引用对其完全不会对其生存时间构成影响,也无法通过虚引用来取得一个对象实例
 *       为一个对象设置虚引用关联的唯一目的就是能在这个对象实例被收集器回收时收到一个系统通知,在JDK 1.2之后，提供了PhantomReference类来实现虚引用
 *
 *3.2)ThreadLocal产生内存泄漏的原因:
 * ThreadLocalMap使用ThreadLocal的弱引用作为key,那么系统 GC 的时候，这个ThreadLocal势必会被回收，
 *               这样一来，ThreadLocalMap中就会出现key为null的Entry，就没有办法访问这些key为null的Entry的value,
 *               如果当前线程再迟迟不结束的话，这些key为null的Entry的value就会一直存在一条强引用链.
 *
 * ThreadLocal里面使用了一个存在弱引用的map(ThreadLocal.ThreadLocalMap),这个Map的key使用了弱引用，每个key都弱引用指向threadloca，
 *              当把threadlocal实例置为null以后，没有任何强引用指向threadlocal实例，所以threadlocal将会被gc回收，我们的value却不能回收，而这块value永远不会被访问到了，所以存在着内存泄露。
 *
 *3.3)ThreadLocal存泄漏的根源是:由于ThreadLocalMap的生命周期跟Thread一样长，如果没有手动删除对应key就会导致内存泄漏，而不是因为弱引用.
 *
 *3.4)避免内存泄漏:每次使用完ThreadLocal，都调用它的remove()方法，清除数据.
 *
 *
 *4)ThreadLocal的子线程和主线程交互共享变量(子线程和主线程的ThreadLocal进行交互)：
 * https://blog.csdn.net/liubenlong007/article/details/107049375
 *
 *
 * 阿里开源transmittable-thread-local(TTL),ThreadLocal的需求场景页就是TransmittableThreadLocal的潜在需求场景，可以看下ThreadLocal的具体运用
 * https://github.com/alibaba/transmittable-thread-local
 */



//ThreadLocalDemo1不实用ThreadLocal的情况
public class ThreadLocalDemo1 {
    public static final Integer SIZE = 500;

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>());

        //设置JVM的运行的参数: -Xms40m -Xmx40m -Xmn10m -XX:+UseParallelGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
        try {
            for (int i = 0; i < SIZE; i++) {
                //1:使用线程池提交五个线程：
                executor.execute(() -> {
                    new LocalVariable();
                    System.out.println("线程:"+Thread.currentThread().getName()+",开始执行");
                });

                //2:这里变成了主线程,在线程池的线程执行完成之后，让主线程休眠100毫秒;
                System.out.println("当前线程的名字为:"+Thread.currentThread().getName());
                Thread.sleep(100);
            }
        }catch(InterruptedException e){

        }
    }

    //占用内存的类;
    static class LocalVariable {
        private byte[] locla = new byte[1024 * 1024 * 5];
    }
}


