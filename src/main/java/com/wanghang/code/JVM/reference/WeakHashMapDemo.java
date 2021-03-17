package com.wanghang.code.JVM.reference;


import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 *软引用和弱引用的使用场景：
 *场景：
 *    假如有一个应用需要读取大量的本地图片,该如何设计呢？
 *    如果每次读取图片都从硬盘读取则会严重影响性能,但是如果使用内存，则又会占用内存,有可能把内存给撑爆，造成系统异常。
 *
 *2)解决方案：
 *    此时使用软引用可以解决这个问题：
 *    设计思路：使用HashMap来保存图片的路径和相应图片对象关联的软引用之间的映射关系，在内存不足时，JVM会自动回收这些缓存图片对象所占的空间，从而有效地避免了OOM的问题
 *    Map<String, SoftReference<String>> imageCache = new HashMap<String, SoftReference<Bitmap>>();
 *
 *
 *3)WeakHashMap:
 *     比如一些常常和底层打交道的，mybatis等，底层都应用到了WeakHashMap;
 *     WeakHashMap和HashMap类似，只不过它的Key是使用了弱引用的，也就是说，当执行GC的时候，HashMap中的key会进行回收;
 *
 *4)代码测试结果：
 *      对于普通的HashMap来说，key置空并不会影响，HashMap的键值对，因为这个属于强引用，不会被垃圾回收;
 *      WeakHashMap的Key是使用了弱引用的，也就是说，当执行GC的时候，HashMap中的key会进行回收，
 *
 */
public class WeakHashMapDemo {

    public static void main(String[] args) {
        //HashMap
        myHashMap();

        //WeakHashMap
        myWeakHashMap();
    }


    //HashMap测试
    private static void myHashMap() {
        Map<Integer, String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "HashMap";
        map.put(key, value);
        System.out.println(map);

        key = null;
        System.gc();     //这个key是强引用(new Integer(),不会被回收掉)，

        System.out.println(map);
    }


    //WeakHashMap的测试
    private static void myWeakHashMap() {
        Map<Integer, String> weakHashMap = new WeakHashMap<>();
        Integer key = new Integer(1);
        String value = "WeakHashMap";
        weakHashMap.put(key, value);
        System.out.println(weakHashMap);

        key = null;
        System.gc();   //执行GC的时候，会将key进行回收，就成了空的map了

        System.out.println(weakHashMap);
    }
}
