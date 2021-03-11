package com.wanghang.code.design.singleton;


/**
 * 懒加载模式的单例模式
 *
 *
 */
public class LazySingleton2 {

    private static final LazySingleton2 INSTANCE=null;


    // 私有构造函数，避免被客户端代码使用
    private LazySingleton2(){
    }


    public static LazySingleton2 getEagerSingleton(){
        if(INSTANCE==null){
            return new LazySingleton2();
        }
        return INSTANCE;
    }
}
