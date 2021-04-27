package com.wanghang.code.guava;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.HashMap;
import java.util.Map;


/**
 *Multimap:
 * Multimap相对于传统的Map区别在于，Multimap是一对多的一个数据结构,
 *       对于Multimap的解析用HashMultimap进行解析，看一下HashMultimap的类结构图
 *
 *参考博文：
 *      https://www.pianshen.com/article/1269148159/
 *
 *
 *
 *
 *
 *
 *
 */
public class MultimapDemo {

    public static void main(String[] args) {
        MultimapDemo multimapDemo=new MultimapDemo();

        multimapDemo.multiMaptest();

        multimapDemo.maptest();

    }

    private  void multiMaptest() {
        Multimap<String, Object> multimap = HashMultimap.create();
        multimap.put("a",1);
        multimap.put("a",2);
        multimap.put("a",3);
        multimap.put("a",4);
        multimap.put("a",5);
        multimap.put("b",6);
        multimap.put("c",7);
        System.out.println("multimap:"+multimap);
        System.out.println("multimapJson:"+ JSONObject.toJSONString(multimap));
    }

    private  void maptest() {
        Map<String, Object> map = new HashMap<>();
        map.put("a",1);
        map.put("a",2);
        map.put("b",3);
        map.put("c",4);
        map.put("d",5);
        map.put("d",6);
        System.out.println("map:"+map);
        System.out.println("mapJson:"+ JSONObject.toJSONString(map));
    }
}
