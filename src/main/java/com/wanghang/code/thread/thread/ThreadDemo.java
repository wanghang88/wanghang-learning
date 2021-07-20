package com.wanghang.code.thread.thread;


import java.util.ArrayList;
import java.util.List;


/**
 *在一个方法内开一个线程,如何调试这个线程的代码？
 *
 * 直接让这个主线程休眠10000,这样就可以debug到子线程的执行逻辑
 *
 */
public class ThreadDemo {


    public static void main(String[] args) throws InterruptedException {
        int a=6;
        int b =10;
        int c=a+b;

        Thread t1=new Thread(new WorkThread());
        t1.start();
        Thread.sleep(900);
    }






    static class WorkThread implements Runnable{
        @Override
        public void run() {
            System.out.println("异步线程执行开始");
            List<String> list=new ArrayList<>();
            list.add("1");
            list.add("2");
            list.add("3");
            list.add("4");
            list.add("5");
            list.add("6");
            list.add("7");
            list.add("8");
            list.add("9");
            list.add("10");
            list.stream().forEach(item->{
                System.out.println("item:"+item);
            });
            System.out.println("异步线程执行结束");

        }
    }
}




