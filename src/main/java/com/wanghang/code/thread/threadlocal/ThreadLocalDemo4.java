package com.wanghang.code.thread.threadlocal;


/**
 * 1:场景;
 * 实际工作中可能会由于任务复杂,父线程创建几个子线程并发致性任务，那么父线程的本地变量如何传递到子线程呢?
 *
 * 2：测试：在主线程的ThreadLocal设置值，但是子线程却并没有取到,
 * ThreadLocal可以保存一些变量仅供当前线程使用，其他线程不可见，如果需要传给子线程,则需要使用InheritableThreadLocal。
 *
 * 3:原理参考博文:
 * https://blog.csdn.net/liubenlong007/article/details/107049375
 */

public class ThreadLocalDemo4{

    public static void main(String[] args) {
        ThreadLocalDemo4 threadLocalDemo4=new ThreadLocalDemo4();
        threadLocalDemo4.threadLocalTest1();


        //子线程可以获取到主线程的ThreadLocal设置的值。
        threadLocalDemo4.threadLocalTest2();
    }

    public void threadLocalTest1(){
        ThreadLocal<String> local = new ThreadLocal<>();
        String localValue=Thread.currentThread().getName()+"ThreadLocal";
        local.set(localValue);
        System.out.println("设置ThreadLocal的值为:"+local.get());
        new Thread(()->{
            System.out.println("子线程为:"+Thread.currentThread().getName()+",获取到ThreadLocal的值为:"+local.get());

        },"t1").start();
    }


    //1:可以看到子线程可以获取到主线程设置的ThreadLocal的值:
    public void threadLocalTest2(){
        ThreadLocal<String> local = new InheritableThreadLocal<>();
        String localValue=Thread.currentThread().getName()+"InheritableThreadLocal";
        local.set(localValue);
        System.out.println("设置ThreadLocal的值为:"+local.get());
        new Thread(()->{
            System.out.println("子线程为:"+Thread.currentThread().getName()+",获取到ThreadLocal的值为:"+local.get());
        },"t2").start();
    }
}
