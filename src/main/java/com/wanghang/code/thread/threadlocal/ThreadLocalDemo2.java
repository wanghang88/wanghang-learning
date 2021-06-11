package com.wanghang.code.thread.threadlocal;


import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//使用ThreadLocal，但不remove
public class ThreadLocalDemo2 {

    public static final Integer SIZE = 500;
    static ThreadLocal<LocalVariable> local = new ThreadLocal<>();

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>());
        try {
            for (int i = 0; i < SIZE; i++) {
                executor.execute(() -> {
                    //1:执行的时候每个子线程都网ThreadLocal占用5M的内存(ThreadLocal是全局的类变量)
                    local.set(new LocalVariable());
                    System.out.println("当前线程名称:"+Thread.currentThread().getName()+",开始执行");
                });
                //2:线程池里的线程执行完成之后,让main线程休眠200毫秒(如果这里不设置休眠的话，下面的local设置为null,后面的线程池子里的线程set的时候会报空指针异常)
                Thread.sleep(200);
            }

            //3:这里设置为null,但是依旧会造成内存泄漏(设置为null,但内存不会释放）
            local=null;
        }catch (InterruptedException e){

        }
    }





    //总共有5M
    static class LocalVariable {
        private byte[] locla = new byte[1024 * 1024 * 5];
    }
}
