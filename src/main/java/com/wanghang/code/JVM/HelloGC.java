package com.wanghang.code.JVM;


import java.util.concurrent.TimeUnit;

/**
 *参考：
     https://gitee.com/moxi159753/LearningNotes/tree/master/%E6%A0%A1%E6%8B%9B%E9%9D%A2%E8%AF%95/JUC/12_JVM/JVM%E9%9D%A2%E8%AF%95%E9%A2%98%E6%B1%87%E6%80%BB/2_JVM%E5%8F%82%E6%95%B0%E8%B0%83%E4%BC%98#%E5%B7%A5%E4%BD%9C%E4%B8%AD%E5%B8%B8%E7%94%A8%E7%9A%84jvm%E5%9F%BA%E6%9C%AC%E9%85%8D%E7%BD%AE%E5%8F%82%E6%95%B0
 *
 *
 *JVM调参系列的相关参数设置:
 *
 * jps：查看java的后台进程
 * jinfo：查看正在运行的java程序
 *
 * 1)
 * jps -l得到进程号
 * jinfo -flag PrintGCDetails 13540
 *
 *
 * 2)Xms和Xmx这两个参数：
 * -Xms:等价于(-XX:InitialHeapSize)初始化堆内存，默认分配为最大物理内存的1/64,
 * -Xmx:等价于(-XX:MaxHeapSize)最大堆内存,默认分配为最大物理内存的1/4,
 * -Xss:等价于(-XX:ThreadStackSize)设置java虚拟机栈内存的大小(jinfo -flag ThreadStackSize 到时候要看下?)
 *      这个值的大小是取决于平台的
 *      Linux/x64:1024KB
 *      OS X：1024KB
 *      Oracle Solaris：1024KB
 *      Windows：取决于虚拟内存的大小
 *
 *  -Xmn:设置青年代的大小
 *  -XX:MatespaceSize= 设置元控件的大小
 *
 *
 *
 * 3)查看JVM的默认参数：
 *-XX:+PrintFlagsInitial  设置这个为启动参数的化,在JVM启动的时候就会将初始化的参数打印出来;
 *-XX:+PrintFlagsFinal    设置这个为启动参数，会在JVM启动的时候将修改过后的参数打印出来
 * java -XX:+PrintFlagsInitial,通过命令查看JVM初始化参数的启动参数;
 * java -XX:+PrintFlagsFinal,  通过命令查看JVM修改过后的参数值；
 *
 * -XX:+PrintCommandLineFlags  设置这个打印出JVM的默认的简单初始化参数
 *
 * 例如：设置-XX:+PrintCommandLineFlags之后，就会将JVM简单的初始化参数
 *-XX:+PrintGCDetails -XX:InitialHeapSize=100m -XX:MaxHeapSize=100m -XX:+UseParallelGC
 *
 *
 * 4)设置JVM虚拟机参数并查看GC的日志：
 *   设置JVM的参数，然后草果其内存，查看其GC垃圾回收情况
 *   -Xms10m -Xmx10m -XX:+PrintGCDetails
 *
 *  通过PrintGCDetails设置查看GC的日志：
 * [GC (Allocation Failure) [PSYoungGen: 1311K->496K(6144K)] 1311K->520K(19968K), 0.0007462 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [GC (Allocation Failure) [PSYoungGen: 496K->432K(6144K)] 520K->464K(19968K), 0.0011100 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [Full GC (Allocation Failure) [PSYoungGen: 432K->0K(6144K)] [ParOldGen: 32K->367K(13824K)] 464K->367K(19968K), [Metaspace: 2923K->2923K(1056768K)], 0.0037420 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
 * [GC (Allocation Failure) [PSYoungGen: 0K->0K(6144K)] 367K->367K(19968K), 0.0003110 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [Full GC (Allocation Failure) [PSYoungGen: 0K->0K(6144K)] [ParOldGen: 367K->350K(13824K)] 367K->350K(19968K), [Metaspace: 2923K->2923K(1056768K)], 0.0032503 secs]
 *
 *
 * GC在新生代的回收：
 * [GC (Allocation Failure) [PSYoungGen: 1311K->496K(6144K)] 1311K->520K(19968K), 0.0007462 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 *
 * FullGC的日志：
 *[Full GC (Allocation Failure) [PSYoungGen: 0K->0K(2560K)] [ParOldGen: 648K->630K(7168K)] 648K->630K(9728K), [Metaspace: 3467K->3467K(1056768K)], 0.0058502 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
 *
 *
 *
 *
 */
public class HelloGC {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("HelloGC");
       // getGCMemory();
       // TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        viewGCLog();


    }

    public static void getGCMemory(){
        // 返回Java虚拟机中内存的总量(初始化的堆内存)
        long totalMemory = Runtime.getRuntime().totalMemory();

        // 返回Java虚拟机中试图使用的最大内存量(分配的最大堆内存)
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("TOTAL_MEMORY(-Xms) = " + totalMemory + "(字节)、" + (totalMemory / (double)1024 / 1024) + "MB");
        System.out.println("MAX_MEMORY(-Xmx) = " + maxMemory + "(字节)、" + (maxMemory / (double)1024 / 1024) + "MB");
    }


    //查看GC日志的实战：
    public static void viewGCLog(){
        byte [] byteArray=new byte[30*1024*1024];
    }
}
