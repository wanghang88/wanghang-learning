package com.wanghang.code.JVM;


/**
 *记录JVM相关的知识：
 *a)GC算法的介绍：
 * https://yuanyu.blog.csdn.net/article/details/87624961
 *
 *b)JVM:
 * https://yuanyu.blog.csdn.net/article/details/87554692
 *
 *
 *c)关于java虚拟机之直接内存(堆外内存)：
 *                1)不是虚拟机运行时数据区的一部分，也不是《Java虚拟机规范》中定义的内存区域，直接内存是Java堆外的，直接向系统申请的内存空间，
 *                    来源于NIO，通过存在堆中的DirectByteBuffer操作Native内存。
 *                2)直接内存也可能导致OOM异常（OutOfMemoryError:Direct buffer memory)；
 *                3)由于直接内存是在Java堆外，因此它的大小不会直接受限于-Xmx指定的最大堆的大小，但是系统内存是有限的，Java堆和直接内存的总和依然受限于操作系统给出的最大内存,
 *                  直接内存的大小可以通过MaxDirectMemorySize设置,如果不指定，默认与堆的最大值-Xmx参数值一致
 *                4)直接内存的缺点：分配回收成本较高；不受JVM内存回收管理
 *                简单理解来看：Java process memory(java程序运行内存) = java heap + native memory
 *                5)堆外内存的垃圾回收：
 *                  堆外内存不会像新生代和老年代那样，发现空间不足就通知收集器进行回收，
 *                    它只能等待老年代满了后触发Full GC，然后JVM会“顺便地”清理堆外内存中废弃的对象，
 *                    在极端情况下，如果JVM一直没有执行Full GC的话， 堆外内存也一直得不到释放。
 *
 *
 */
public class JVMDemo {

    public static void main(String[] args) {




    }
}
