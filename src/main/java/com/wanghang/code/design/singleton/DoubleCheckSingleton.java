package com.wanghang.code.design.singleton;


/**
 *线程安全，双端检索的单粒模式
 *
 */
public class DoubleCheckSingleton {

    private static volatile DoubleCheckSingleton INSTANCE=null;

    //私有构造函数
    private DoubleCheckSingleton(){

    }


    public static synchronized DoubleCheckSingleton getDoubleCheckSingleton(){
        if(INSTANCE==null){
            synchronized (DoubleCheckSingleton.class){
                if(INSTANCE==null){
                    INSTANCE=new DoubleCheckSingleton();
                }
            }
        }
        return INSTANCE;
    }
}
