package com.wanghang.code.JVM;


/**
 *GC:
 *java中的垃圾：
 *           内存中已经不再被使用的空间就是垃圾
 *
 * 垃圾回收哪些对象判断可以回收？
 * 引用计数法：引用和对象是有关联的,一个简单的办法就是通过引用计数来判断一个对象是否可以回收,
 *           每当有一个地方引用它，计数器值加1,每当有一个引用失效，计数器值减1,任何时刻计数器值为零的对象就是不可能再被使用的，那么这个对象就是可回收对象
 *           一般主流的java虚拟机不会采用这种方法，因为很难解决循环引用的问题
 *
 *枚举根节点做可达性分析(GC Roots,就是一组必须活跃的引用)：
 *           通过一系列名为 GC Roots的对象作为起始点，从这个被称为GC Roots的对象开始向下搜索，如果一个对象到GC Roots没有任何引用链相连，则说明此对象不可用
 *
 *哪些对象可以作为GCRoot对象：
 * 1)虚拟机栈（栈帧中的局部变量区，也叫做局部变量表）中的引用对象;
 * 2)方法区中的类静态属性引用的对象
 * 3)方法区中常量引用的对象
 * 4)本地方法栈中的JNI（Native方法）的引用对象
 *
 *
 *
 *2)关于JVM面试的总结不错的博客(基本上jvm的知识点都有)
 * https://joonwhee.blog.csdn.net/article/details/79692477
 *
 *
 *
 */
public class GCRootDemo {

    // 方法区中的类静态属性引用的对象
    private static GCRootDemo t2;

    // 方法区中的常量引用，GC Roots 也会以这个为起点，进行遍历
    private static final GCRootDemo t3 = new GCRootDemo();

    public static void m1() {
        // 第一种，虚拟机栈中的引用对象
        GCRootDemo t1 = new GCRootDemo();
        System.gc();
        System.out.println("第一次GC完成");
    }

    public static void main(String[] args) {
        m1();
    }
}
