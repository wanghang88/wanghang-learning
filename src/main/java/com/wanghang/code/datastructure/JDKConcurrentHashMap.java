package com.wanghang.code.datastructure;

import java.util.concurrent.ConcurrentHashMap;


/**
 * JDK 1.8以后的ConcurrentHashMap:
 * 采用：数组+cas+synchronizer+红黑树
 *
 *
 */
public class JDKConcurrentHashMap {


    public static void main(String[] args) {
        ConcurrentHashMap<String,Object> concurrentHashMap=new ConcurrentHashMap<>();

        concurrentHashMap.put("a",1);
        concurrentHashMap.put("b",2);
        concurrentHashMap.put("c",3);

        System.out.println("concurrentHashMap:"+concurrentHashMap);
    }
}
