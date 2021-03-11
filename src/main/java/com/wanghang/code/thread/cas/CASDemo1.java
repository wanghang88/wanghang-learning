package com.wanghang.code.thread.cas;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo1 {

    private int number=0;
    private AtomicInteger atomicInteger=new AtomicInteger(0);


    public void setCount(){
        number++;
    }
    public void setSafeCount(){
        for (;;){
           /* int i = atomicInteger.get();
            boolean compareAndSetBool = atomicInteger.compareAndSet(i, i++);
            if (compareAndSetBool){
                break;
            }*/
            atomicInteger.getAndIncrement();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        final CASDemo1 cASDemo1=new CASDemo1();
        List<Thread> threadList=new ArrayList<>(100);

         for (int i = 1; i < 100; i++) {
             Thread thread= new Thread(new Runnable() {
               @Override
               public void run() {
                    for (int i = 1; i <100 ; i++) {
                        cASDemo1.setCount();
                        cASDemo1.setSafeCount();
                    }
               }
           });
             threadList.add(thread);
         }

         for (Thread thread:threadList){
             thread.start();
         }

         //等待所有线程执行完成
         for (Thread thread:threadList){
             thread.join();
         }

         System.out.println("number:"+cASDemo1.number);
         System.out.println("atomicInteger:"+cASDemo1.atomicInteger.get());
    }
}
