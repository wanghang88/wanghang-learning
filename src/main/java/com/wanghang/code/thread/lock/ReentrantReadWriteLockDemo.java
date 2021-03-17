package com.wanghang.code.thread.lock;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *读写锁：
 * 1)概念：
 *     独占锁，指该锁一次只能被一个线程所持有。对ReentrantLock和Synchronized而言都是独占锁
 *     共享锁，指该锁可以被多个线程锁持有
 *     对ReentrantReadWriteLock其读锁是共享，其写锁是独占，写的时候只能一个人写，但是读的时候，可以多个人同时读。
 * 2)为什么会有读写锁：
 *     使用ReentrantLock创建锁的时候，是独占锁，也就是说一次只能一个线程访问，但是有一个读写分离场景，
 *                     读的时候想同时进行，因此原来独占锁的并发性就没这么好了，
 *                     多线程读的话并不会造成数据的不一致，因此可以多人共享
 *
 *     对于共享资源的操作(读和写同时进行)，比如：火车飞机的航班大盘信息，读和写是同时进行的。
 *
 *     多个线程 同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行，
 *            但是如果一个线程想去写共享资源，就不应该再有其它线程可以对该资源进行读或写(多线程去读的话是可以同时进行的，但是只要有一个线程去写的话，其他线程就不能对资源进行读写操作)
 *
 *      读-读 能共存
 *      读-写 不能共存
 *      写-写 不能共存
 *
 * 3)编写代码测试，对HashMap同时并发地进行读和写
 *     a）不安全的并发读写会造成：
 *                        我们可以看到，在写入的时候，写操作都没其它线程打断了，这就造成了，还没写完，其它线程又开始写，这样就造成数据不一致
 *
 *     b)加入读写锁(ReentrantReadWriteLock)地进行并发读写：
 *
 *
 *4)ReentrantReadWriteLock的缺点不足：
 *    写的时候写锁没有释放，读操作不能进行(写->读)
 *    读操作的时候，读锁没有释放，也不能写，一种悲观的锁的实现（读->写）
 *    只有多线程并发读的时候才可以
 *
 * 5)解决方案：StampedLock
 */
public class ReentrantReadWriteLockDemo {

    public static void main(String[] args) {

        ReentrantReadWriteLockDemo reentrantReadWriteLockDemo=new ReentrantReadWriteLockDemo();
    //  reentrantReadWriteLockDemo.operationUnsafeHashMap();

    //  reentrantReadWriteLockDemo.operationSafeHashMap();

        reentrantReadWriteLockDemo.operationSafeHashMap2();
    }


    //1:执行不安全的HashMap并发读写操作
    private void operationUnsafeHashMap() {
        UnsafeCache unsafeCache = new UnsafeCache();

        // 线程操作资源类，5个线程写
        for (int i = 0; i < 5; i++) {
            // lambda表达式内部必须是final
            final int tempInt = i;
            new Thread(() -> {
                unsafeCache.put(tempInt + "", tempInt +  "");
            }, String.valueOf(i)).start();
        }


        // 线程操作资源类， 5个线程读
        for (int i = 0; i < 5; i++) {
            // lambda表达式内部必须是final
            final int tempInt = i;
            new Thread(() -> {
                unsafeCache.get(tempInt + "");
            }, String.valueOf(i)).start();
        }
    }

    //3:执行安全的HashMap并发读写操作的实例2
    private void operationSafeHashMap2() {
        //1:初始化缓存
        SafeCache safeCache = new SafeCache();
        for (int i = 1; i <=100 ; i++) {
             safeCache.put(i+"",i+"");
         }

        //2:实用化读写锁操作缓存
        for (int i = 1; i <=100; i++) {
            // lambda表达式内部必须是final
            final int tempInt = i;
            new Thread(() -> {
                if(tempInt%5==0){
                    safeCache.put(tempInt + "", tempInt +"");
                }else {
                    safeCache.get(tempInt + "");
                }
            }, String.valueOf(i)).start();
        }
    }

    private void operationSafeHashMap() {
        SafeCache safeCache = new SafeCache();

        //2.1)线程操作资源类，5个线程写
        for (int i = 1; i <=5; i++) {
            // lambda表达式内部必须是final
            final int tempInt = i;
            new Thread(() -> {
                safeCache.put(tempInt + "", tempInt +"");
            }, String.valueOf(i)).start();
        }

        //2.2)线程操作资源类， 5个线程读
        for (int i = 1; i <=10; i++) {
            // lambda表达式内部必须是final
            final int tempInt = i;
            new Thread(() -> {
                safeCache.get(tempInt +"");
            }, String.valueOf(i)).start();
        }
    }
}



//不安全的缓存，操作HashMap
class UnsafeCache{
    private volatile Map<String, Object> map = new HashMap<>();


    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + "\t 正在写入：" + key);
        try {
            // 模拟网络拥堵，延迟0.3秒
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "\t 写入完成");
    }


    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + "\t 正在读取:");
        try {
            // 模拟网络拥堵，延迟0.3秒
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object value = map.get(key);
        System.out.println(Thread.currentThread().getName() + "\t 读取完成：" + value);
    }
}


//加入读写多,安全地对HashMap进行读写操作：
class SafeCache{
    /**
     * 缓存中的东西，必须保持可见性，因此使用volatile修饰
     */
    private volatile Map<String, Object> map = new HashMap<>();

    /**
     * 创建一个读写锁
     * 它是一个读写融为一体的锁，在使用的时候，需要转换
     */
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();


    public void put(String key, Object value) {
        // 创建一个写锁
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入：" + key);
            try {
                // 模拟网络拥堵，延迟0.3秒
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 写锁 释放
            rwLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        // 读锁
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取:");
            try {
                // 模拟网络拥堵，延迟0.3秒
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object value = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成：" + value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 读锁释放
            rwLock.readLock().unlock();
        }
    }


    /**
     * 清空缓存
     */
    public void clean() {
        map.clear();
    }
}
