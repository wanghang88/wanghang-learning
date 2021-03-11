package com.wanghang.code.design.singleton;

public class EagerSingleton1 {

    private static final EagerSingleton1 INSTANCE=new EagerSingleton1();


    // 私有构造函数，避免被客户端代码使用
    private EagerSingleton1(){
    }

    public static EagerSingleton1 getEagerSingleton(){
        return INSTANCE;
    }
}
