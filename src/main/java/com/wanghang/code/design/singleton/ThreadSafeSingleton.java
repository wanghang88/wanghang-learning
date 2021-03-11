package com.wanghang.code.design.singleton;


/**
 *线程安全的单例模式，只是在懒加载的模式上加了同步
 *
 */
public class ThreadSafeSingleton {

    private static  ThreadSafeSingleton INSTANCE=null;


    //私有构造函数
    private ThreadSafeSingleton(){

    }

    public  static synchronized ThreadSafeSingleton getThreadSafeSingleton(){
        if(INSTANCE==null){
            INSTANCE= new ThreadSafeSingleton();
        }
        return INSTANCE;
    }
}
