package com.wanghang.code.JVM.reference;


import java.lang.ref.SoftReference;

/**
 *
 *1)软引用(SoftReference):软引用是一种相对弱化了一些的引用，需要用Java.lang.ref.SoftReference类来实现
 *
 *2)对于软引用来讲：
 *            当系统内存充足时，它不会被回收,当系统内存不足时，它会被回收
 *
 *3)软引用的具体运用：
 *            软引用通常在对内存敏感的程序中，比如缓存,内存够用 的时候就保留，不够用就回收。
 *
 *
 *4)示例代码：
 */
public class SoftReferenceDemo {

    public static void main(String[] args) {

        softRefMemoryEnough();

        softRefMemoryNoEnough();
    }


    //1:软引用在内存足够的情况下,触发GC是不会被回收的
    private static void softRefMemoryEnough() {
        // 创建一个强应用
        Object o1 = new Object();
        // 创建一个软引用
        SoftReference<Object> softReference = new SoftReference<>(o1);

        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        // 手动GC
        System.gc();

        //这个GC会把对象o1回收掉,但是软引用softReference里的objec不会被回收，因为内存足够
        System.out.println(o1);
        System.out.println(softReference.get());
    }



    /**
     * 2:软引用在内存不够用触发GC的情况下，会被回收掉
     *
     * JVM配置，故意产生大对象并配置小的内存，让它的内存不够用了导致OOM，看软引用的回收情况
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    private static void softRefMemoryNoEnough() {

        // 创建一个强应用
        Object o1 = new Object();
        // 创建一个软引用
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        // 模拟OOM自动GC
        try {
            // 创建30M的大对象
            byte[] bytes = new byte[30 * 1024 * 1024];
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }
}
