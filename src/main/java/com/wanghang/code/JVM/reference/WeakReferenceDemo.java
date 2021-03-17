package com.wanghang.code.JVM.reference;


import java.lang.ref.WeakReference;

/**
 *弱引用(WeakReference):
 *     弱引用需要用 java.lang.ref.WeakReference 类来实现，它比软引用生存期更短,
 *     不管内存是否够，只要有GC操作就会进行回收
 */
public class WeakReferenceDemo {

    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);

        System.out.println(o1);
        System.out.println(weakReference.get());

        //o1设置为null并且手动触发Gc
        o1 = null;
        System.gc();

        System.out.println(o1);
        System.out.println(weakReference.get());
    }
}
