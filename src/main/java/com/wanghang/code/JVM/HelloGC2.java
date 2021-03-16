package com.wanghang.code.JVM;


import java.util.ArrayList;
import java.util.List;

/**
 *在YGC执行前的空间担保
 * 空间分配担保,在YGC之前：
 *                  会先检查老年代最大可用的连续空间是否大于新生代所有对象的总空间,
 *                  小于，说明YGC是不安全的,
 *                  则会查看参数HandlePromotionFailure 是否被设置成了允许担保失败，
 *                  如果不允许则直接触发Full GC
 *                  如果允许，那么会进一步检查老年代最大可用的连续空间是否大于历次晋升到老年代对象的平均大小，如果小于也会触发 Full GC
 *
 * 在YGC执行前,目前新生代已使用的大小>老年代剩余空间大小? 直接执行Full GC(不执行YGC) : 执行YGC
 * 在YGC执行后，平均晋升到老年代的大小>老年代剩余空间大小? 触发Full GC:null
 *
 *需求：
 * 参考:
 *     https://blog.csdn.net/chenhailonghp/article/details/93614904
 *
 *     让其运行时的表现为触发5次ygc，然后3次fgc，然后3次ygc，然后1次fgc，请给出代码以及启动参数。
 *运行参数：
 * -Xms41m -Xmx41m -Xmn10m -XX:+UseParallelGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
 *
 *
 */
public class HelloGC2 {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        System.out.println("0.---");
        List caches = new ArrayList();
        for (int i = 0; i < 11; i++){
            caches.add(new byte[3 * _1MB]);
        }

        System.out.println("1.---");
        caches.add(new byte[3 * _1MB]);
        caches.remove(0);
        caches.add(new byte[3 * _1MB]);
        for (int i = 0; i < 8; i++) {
            caches.remove(0);
        }


        caches.add(new byte[3 * _1MB]);
        System.out.println("2.---");
        for (int i = 0; i < 7; i++){
            caches.add(new byte[3 * _1MB]);
        }
    }
}
