package com.wanghang.code.thread.lock;


import java.util.concurrent.TimeUnit;

/**
 *https://gitee.com/moxi159753/LearningNotes/tree/master/%E6%A0%A1%E6%8B%9B%E9%9D%A2%E8%AF%95/JUC/11_%E6%AD%BB%E9%94%81%E7%BC%96%E7%A0%81%E5%8F%8A%E5%BF%AB%E9%80%9F%E5%AE%9A%E4%BD%8D
 *死锁：
 *   两个或这两个以上的进程在执行过程中,因争夺资源而造成一种互相等待的现象,若无外力干涉那他们都将无法推进下去.
 *
 *产生的原因：
 *   系统资源不足
 *   进程运行推进的顺序不对
 *   资源分配不当
 *
 *产生的四个必要条件：
 *   互斥
 *       解决办法,把互斥的共享资源封装成可同时访问,
 *   占有且等待
 *       解决办法：进程请求资源时，要求它不占有任何其它资源，也就是它必须一次性申请到所有的资源，这种方式会导致资源效率低,
 *   非抢占式
 *       解决办法：如果进程不能立即分配资源，要求它不占有任何其他资源，也就是只能够同时获得所有需要资源时，才执行分配操作
 *   循环等待：
 *       解决办法：对资源进行排序，要求进程按顺序请求资源
 *
 *思索产生的问题分析：
 *   jps -l  查看运行的程序
 *   jstack pid查看线程的堆栈信息
 */

class HoldLockThread implements Runnable{

    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t"+"持有自己的锁:"+lockA+",尝试获取其他锁:"+lockB);

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {

            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t"+"持有自己的锁:"+lockB+",尝试获取其他锁:"+lockA);
            }
        }
    }
}






public class DeadLockDemo {

    public static void main(String[] args) {
        new Thread(new HoldLockThread("lockA","lockB"),"t1").start();
        new Thread(new HoldLockThread("lockB","lockA"),"t2").start();

    }

}
