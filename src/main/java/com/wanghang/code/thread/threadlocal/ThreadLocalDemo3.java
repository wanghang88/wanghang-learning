package com.wanghang.code.thread.threadlocal;


import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//使用Thread Local，且remove
public class ThreadLocalDemo3 {
    public static final Integer SIZE = 500;

    public static void main(String[] args) {
        ThreadLocal<ThreadLocalDemo2.LocalVariable> local = new ThreadLocal<>();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>());
        try {
            for (int i = 0; i < SIZE; i++) {
                executor.execute(() -> {
                    //1:执行的时候每个子线程都网ThreadLocal占用5M的内存(ThreadLocal是全局的类变量)
                    local.set(new ThreadLocalDemo2.LocalVariable());
                    System.out.println("当前线程名称:"+Thread.currentThread().getName()+",开始执行");
                    //2:使用完成之后删除ThreadLocal变量;
                    local.remove();
                });
                //3:线程池里的线程执行完成之后,让main线程休眠200毫秒
                Thread.sleep(200);
            }
        }catch (InterruptedException e){

        }
    }




    //总共有5M
    static class LocalVariable {
        private byte[] locla = new byte[1024 * 1024 * 5];
    }
}
