package com.wanghang.code.thread.thread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
  1)UncaughtExceptionHandler的作用：
  当线程由于未捕获异常即将中止时，JVM将使用thread.getuncaughtexceptionhandler()查询线程的uncaughtException处理程序，并调用处理程序的uncaughtException方法，
  将线程和异常作为参数传递。如果一个线程没有显式地设置它的UncaughtExceptionHandler，
  那么它的ThreadGroup对象就充当它的UncaughtExceptionHandler。
  如果ThreadGroup对象没有处理异常的特殊要求，它可以将调用转发给默认的未捕获异常处理程序。

  总体来说：当线程由于一个未捕获的异常突然中止时调用的处理程序的接口。

  2)UncaughtExceptionHandler的典型使用的场景:
  通过上述结果可以看到，该未知的异常被成功捕获到，我们可以在uncaughtException()方法中做一些释放资源、连接的操作，避免由于线程的突然中止导致资源无法释放。


 3)参考博文：
 博主:(恐龙弟旺仔)
 https://blog.csdn.net/qq_26323323/article/details/104587345






 */
public class UncaughtExceptionHandlerDemo {


    public static void main(String[] args) {

        //1:通过try-catch来抓捕线程执行中的异常，抓不到,因为在多线程环境下,线程抛出的异常是无法用try...catch捕获的。
        test1();


       //2：使用UncaughtExceptionHandler处理，可以看到线程在uncaughtException()捕获到了。
        test2();


        //3:线程池中使用UncaughtExceptionHandler,如果要使UncaughtExceptionHandler定义生效，需要在run()方法内部定义。
        test3();


    }




    private static void test1() {
        try {
            Thread thread = new Thread(new Task());
            thread.start();
        } catch (Exception e) {
            System.out.println("==Exception: " + e.getMessage());
        }
    }



    private static void test2() {
        Thread thread = new Thread(new Task());
        thread.setUncaughtExceptionHandler(new LocalUncaughtExceptionHandler());
        thread.start();
    }


    private static void test3() {
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new ThreadPoolTask());
        exec.shutdown();
    }



    //线程任务
    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println(3 / 2);
            System.out.println(3 / 0);
            System.out.println(3 / 1);
        }
    }



   //线程池任务
   static class ThreadPoolTask implements Runnable {
       @Override
       public void run() {
           // 需要在这里定义UncaughtExceptionHandler
           Thread.currentThread().setUncaughtExceptionHandler(new LocalUncaughtExceptionHandler());
           System.out.println(3 / 2);
           System.out.println(3 / 0);
           System.out.println(3 / 1);
       }
   }


    //捕捉线程在执行过程中的异常
    static class LocalUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("==Exception: "+e.getMessage());
            // do sth... close connection/release resource and so on.
        }
    }
}
