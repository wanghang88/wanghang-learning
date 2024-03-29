package com.wanghang.code.JVM;


/**
  JVM:整体的大概介绍
   https://yuanyu.blog.csdn.net/article/details/87554692

  JVM 整体知识的整体总结(模块)
  https://blog.csdn.net/yxh13521338301/category_10073254.html


  1:JVM的类加载
   JVM的类加载以及双亲委派的介绍:
   https://mp.weixin.qq.com/s/7aAu_blw6hrNtF17W0H2ag
   了解双亲委派的机制然后打破双亲委派的介绍:
   https://mp.weixin.qq.com/s/HHBL8VI5Y_ZM20Kje8BIZQ


  2:JVM的运行数据划分
  JVM主要关于jvm的类的加载，运行时数据划分，class的结构
  https://blog.csdn.net/luanlouis/category_2630089.html


  3:JVM的垃圾回收和垃圾回收算法:
   JVM的垃圾回收算法
   https://yuanyu.blog.csdn.net/article/details/87624961




 4:JVM的调优以及相关的问题排查:
   JVM的常见的参数
   https://mp.weixin.qq.com/s/XZFEgf1ZS7gNt7lku3TF4g




  5:JVM调优:
  主要是java的参数的设置
  https://mp.weixin.qq.com/s/7H_N9uv6RloSg-uk00eMrQ

  主要是JVM的常用的民命令,查找性能点
  https://mp.weixin.qq.com/s/tjTWWxgAq8gj5PK2nzMGnQ
  比如：CPU过高问题的排查(jstack命令)
  第一步：第一步先找出Java进程ID，我部署在服务器上的Java应用名称为mrf-center
         ps -ef | grep mrf-center | grep -v grep

  第二步：第二步找出该进程内最耗费CPU的线程,
         ps -Lfp pid(或者top -Hp pid)

  第三部：轮到jstack上场,它用来输出线程的堆栈信息,
        jstack 21711(线程的id)







  6:JVM常见面试题的哔哩哔哩的视频:
  https://www.bilibili.com/video/BV1854y1H7nR?spm_id_from=333.788.b_636f6d6d656e74.6





 7:JVM常见的面试题目












  c)关于java虚拟机之直接内存(堆外内存)：
                 1)不是虚拟机运行时数据区的一部分，也不是《Java虚拟机规范》中定义的内存区域，直接内存是Java堆外的，直接向系统申请的内存空间，
                     来源于NIO，通过存在堆中的DirectByteBuffer操作Native内存。
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
 *      关于JVM的方法区(元空间)和运行时常量池的参考博文:
 *                                             https://blog.csdn.net/tianzhonghaoqing/article/details/109757341
 *      java虚拟机之方法区和运行时常量池：
 *                方法区:1)与Java堆一样，是各个线程共享的内存区域,它用于存储已被虚拟机加载的类信息、常量、静态变量、即时编译器编译后的
 *                      代码等数据(是唯一的数据),可通过参数-XX:MaxPermSize设置
 *                      方法区主要存放java类定义信息，与垃圾回收关系不大，方法区可以选择不实现垃圾回收,但不是没有垃圾回收。方法区域的内存回收目标主要是针对常量池的回收和对类型的卸载。
 *                      比如:当java虚拟机通过类加载器加载这个类的时候,这个类的信息就会保存到方法区中
 *                常量池：
 *                     2)
 *                       常量池(Constant Pool):常量池数据编译期被确定，是Class文件中的一部分。存储了类、方法、接口等中的常量，当然也包括字符串常量
 *                       运行时常量池(String Constant Pool)：也是方法区的一部分，虚拟机加载Class后把常量池中的数据放入运行时常量池,常量池：可以理解为Class文件之中的资源仓库，
 *                                  它是Class文件结构中与其他项目资源关联最多的数据类型,可通过参数-XX:PermSize和-XX:MaxPermSize设置
 *                       字符串常量池(Runtime Constant Pool): 是常量池中的一部分，存储编译期类中产生的字符串类型数据。
 *                       常量池总结：
 *                               常量池中主要存放两大类常量，字面量（Literal）和符号引用（Symbolic Reference）
 *                               字面量：文本字符串、声明为final的常量值等,
 *                               符号引用：类和接口的完全限定名（Fully Qualified Name）、字段的名称和描述符（Descriptor）、方法的名称和描述符.
 *                        并且jdk1.6之前字符串常量池在方法去中，JDK1.7之后字符串常量池被挪到了堆中
 *
 *
 */
public class JVMDemo {

    public static void main(String[] args) {




    }
}
