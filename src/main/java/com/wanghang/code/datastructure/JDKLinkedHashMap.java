package com.wanghang.code.datastructure;

import java.util.LinkedHashMap;
import java.util.Map;

public class JDKLinkedHashMap {


    public static void main(String[] args) {

        Map<Integer,Object> linkedHashMap=new LinkedHashMap<>();
        linkedHashMap.put(1,1);
        linkedHashMap.put(2,2);
        linkedHashMap.put(3,3);
        linkedHashMap.put(4,1);
        linkedHashMap.put(5,1);
        linkedHashMap.put(6,1);

        System.out.println("linkedHashMap:"+linkedHashMap);
    }
}
