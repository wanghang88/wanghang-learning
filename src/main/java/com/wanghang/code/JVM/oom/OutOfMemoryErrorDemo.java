package com.wanghang.code.JVM.oom;


import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 *堆内存溢出相关的case：
 *
 * 1)堆内存不够:java.lang.OutOfMemoryError: Java heap space
 * 2)oom之:GC overhead limit exceeded，GC回收时间过长时会抛出OutOfMemoryError，过长的定义是，超过了98%的时间用来做GC，并且回收了不到2%的堆内存
 *         连续多次GC都只回收了不到2%的极端情况下,才会抛出
 *
 *3)oom之：Direct buffer memory
 *        Netty + NIO：这是由于NIO引起的,写NIO程序的时候经常会使用ByteBuffer来读取或写入数据,这是一种基于通道(Channel) 与 缓冲区(Buffer)的I/O方式
 *                     它可以使用Native 函数库直接分配堆外内存,然后通过一个存储在Java堆里面的DirectByteBuffer对象作为这块内存的引用进行操作。
 *                     这样能在一些场景中显著提高性能，因为避免了在Java堆和Native堆中来回复制数据
 *
 *       ByteBuffer.allocate(capability)：第一种方式是分配JVM堆内存，属于GC管辖范围，由于需要拷贝所以速度相对较慢；
 *       ByteBuffer.allocteDirect(capability)：第二种方式是分配服务器本地内存，不属于GC管辖范围，由于不需要内存的拷贝，所以速度相对较快；
 *       但如果不断分配本地内存，堆内存很少使用，那么JVM就不需要执行GC，DirectByteBuffer对象就不会被回收，这时候堆内存充足，但本地内存可能已经使用光了，再次尝试分配本地内存就会出现OutOfMemoryError
 *       -XX:MaxDirectMemorySize 通过这个参数指定堆外内存的大小
 *4)oom之：unable to create new native thread
 *        一个应用进程创建多个线程，超过系统承载极限，
 *        服务器并不允许你的应用程序创建这么多线程，linux系统默认运行单个进程可以创建的线程为1024个，
 *           如果应用创建超过这个数量，就会报 java.lang.OutOfMemoryError:unable to create new native thread
 *
 *
 *5)oom之：
 *
 *
 */
public class OutOfMemoryErrorDemo {

    public static void main(String[] args) {

        //1:Java heap space
     //   javaHeapSpace();

        //2：overhead limit exceeded
    //    gCOverheadLimitExceededDemo();

        //3:java.lang.OutOfMemoryError: Direct buffer memory
   //     gCDirectBufferMemory();

        //4:
        unableToCreateNewNativeThread();
    }



    /**
     *1：oom之堆内存不足Java heap space
     *  堆空间的大小 -Xms10m -Xmx10m,
     *  创建一个 80M的字节数组
     *
     */
    private static void javaHeapSpace() {
        byte [] bytes = new byte[80 * 1024 * 1024];
    }


    /**
     *2：oom之GC overhead limit exceeded
     *   GC长时间进行垃圾回收(多次回收),过长的定义是，超过了98%的时间用来做GC，并且回收了不到2%的堆内存
     * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
     *
     */
    private static void gCOverheadLimitExceededDemo() {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while(true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Exception e) {
            System.out.println("***************i:" + i);
            e.printStackTrace();
            throw e;
        } finally {


        }
    }

    /**
     *3：oom之 Direct buffer memory
     *   Netty + NIO技术，对外内存不足造成的
     *   -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
     */
    private static void gCDirectBufferMemory() {
        // 只设置了5M的物理内存使用，但是却分配 6M的空间
        ByteBuffer bb = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }


    /**
     *4:oom之：unable to create new native thread
     *
     *
     *
     *
     *
     */
    private static void unableToCreateNewNativeThread() {



    }



}
