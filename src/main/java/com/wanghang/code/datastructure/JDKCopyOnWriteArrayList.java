package com.wanghang.code.datastructure;

import java.util.concurrent.CopyOnWriteArrayList;

public class JDKCopyOnWriteArrayList {


    public static void main(String[] args) {
        CopyOnWriteArrayList copyOnWriteArrayList=new CopyOnWriteArrayList<>();

        copyOnWriteArrayList.add(1);
        copyOnWriteArrayList.add(2);
        copyOnWriteArrayList.add(3);
    }
}
