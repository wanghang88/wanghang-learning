package com.wanghang.code.JVM.reference;


/**
 *参考：
 * https://gitee.com/moxi159753/LearningNotes/tree/master/%E6%A0%A1%E6%8B%9B%E9%9D%A2%E8%AF%95/JUC/12_JVM/JVM%E9%9D%A2%E8%AF%95%E9%A2%98%E6%B1%87%E6%80%BB/3_Java%E4%B8%AD%E7%9A%84%E5%BC%BA%E5%BC%95%E7%94%A8_%E8%BD%AF%E5%BC%95%E7%94%A8_%E5%BC%B1%E5%BC%95%E7%94%A8_%E8%99%9A%E5%BC%95%E7%94%A8%E5%88%86%E5%88%AB%E6%98%AF%E4%BB%80%E4%B9%88
 *
 * Java中的引用：
 *     Person p = new Person()，
 *     在等号的左边，就是一个对象的引用，存储在栈中,
 *     而等号右边，就是实例化的对象，存储在堆中,
 *     其实这样的一个引用关系，就被称为强引用
 *
 *强引用：
 *     当内存不足的时候，JVM开始垃圾回收，对于强引用的对象，就算是出现了OOM也不会对该对象进行回收，
 *     当一个对象被强引用变量引用时，它处于可达状态，它是不可能被垃圾回收机制回收的，即使该对象以后永远都不会被用到，
 *     JVM也不会回收，因此强引用是造成Java内存泄漏的主要原因之一。
 */
public class StrongReferenceDemo {

    public static void main(String[] args) {
        // 这样定义的默认就是强应用
        Object obj1 = new Object();
        // 使用第二个引用，指向刚刚创建的Object对象
        Object obj2 = obj1;

        System.out.println(obj1);
        System.out.println(obj2);

        // 置空
        obj1 = null;

        // 垃圾回收
        System.gc();
        System.out.println(obj1);
        System.out.println(obj2);
    }
}
