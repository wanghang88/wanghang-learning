package com.wanghang.code.thread.lock;


import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static void main(String[] args) {
        ReentrantLock lock=new ReentrantLock();
        lock.lock();
         try{

         }catch (Exception e){

         }finally {
             lock.unlock();
         }
    }
}




