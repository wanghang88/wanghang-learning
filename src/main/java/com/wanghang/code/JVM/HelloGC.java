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
 *  -XX:MatespaceSize=    设置初始的元空间的大小，达到该值就会触发垃圾收集进行类型卸载，同时GC会对该值进行调整
 *  -XX:MaxMetaspaceSize= 设置初始的元空间的最大值,默认是没有限制的
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
 * 5):JVM 触发YGC(Minor GC)和FullGc的条件:
 *         新生代中的对象朝生夕死，所以 Minor GC 非常频繁，回收速度也比较快,只回收年轻代中的Eden区
 *         指发生在老年代的GC，速度一般比 Minor GC 慢十倍以上。Full GC 会 Stop-The-World,收集整个堆，包括新生代，老年代，永久代
 *
 *
 *  a)Minor GC的触发条件,
 *                 大多数情况下，对象直接在年轻代中的Eden区进行分配，如果Eden区域没有足够的空间，那么就会触发YGC(Minor GC);
 *  b)进入老年代的途径:
 *                 1)经过多次YGC后，如果存活对象的年龄达到了设定阈值，则会晋升到老年代中.
 *                 2)大对象：由-XX:PretenureSizeThreshold启动参数控制，若对象大小大于此值，就会绕过新生代, 直接在老年代中分配.
 *  c)触发FullGc的条件：
 *                 1)老年代的内存使用率达到了一定阈值（可通过参数调整），直接触发FGC;
 *                 2)Metaspace（元空间）在空间不足时会进行扩容，当扩容到了-XX:MetaspaceSize 参数的指定值时，也会触发FGC;
 *                 3)System.gc() 或者Runtime.gc() 被显式调用时，触发FGC;
 *                 4)空间分配担保,在YGC之前，会先检查老年代最大可用的连续空间是否大于新生代所有对象的总空间
 *                              小于，说明YGC是不安全的,则会查看参数 HandlePromotionFailure 是否被设置成了允许担保失败，如果不允许则直接触发Full GC
 *                              如果允许，那么会进一步检查老年代最大可用的连续空间是否大于历次晋升到老年代对象的平均大小，如果小于也会触发 Full GC
 *
 *
 *
 *
 *
 *
 *
 *
 *
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
