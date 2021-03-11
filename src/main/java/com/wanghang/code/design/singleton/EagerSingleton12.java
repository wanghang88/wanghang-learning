package com.wanghang.code.design.singleton;

public class EagerSingleton12 {

    private static final  EagerSingleton12 INSTANCE;

    //私有构造函数
    private EagerSingleton12(){

    }

    static {
        try {
            INSTANCE=new EagerSingleton12();
            }catch (Exception e){
            throw new RuntimeException("Uffff, i was not expecting this!", e);
        }
    }

    public static EagerSingleton12 getEageSingleton(){
        return INSTANCE;
    }
}
