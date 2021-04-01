package com.wanghang.code.thread.threadlocal;


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
 *3)ThreadLocal的子线程和主线程交互共享变量(子线程和主线程的ThreadLocal进行交互)：
 * https://blog.csdn.net/liubenlong007/article/details/107049375
 */

public class ThreadLocalDemo {


    public static void main(String[] args) {




    }
}
