package com.wanghang.code.datastructure;

import java.util.HashMap;
import java.util.Map;

public class JDK18HashMap {

    public static void main(String[] args) {

        HashMap<String,Object> hashMap=new HashMap();
        hashMap.put("1",1);
        hashMap.put("1",2);
        hashMap.put("17",3);
        System.out.println("hashMap:"+hashMap+"hashMap的长度:"+hashMap.size());
    }
}
