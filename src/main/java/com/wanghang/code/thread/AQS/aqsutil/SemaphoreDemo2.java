package com.wanghang.code.thread.AQS.aqsutil;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 *基于Semaphore，CountDownLatch以及线程池实现模拟并发请求的case
 *
 * 总请求数是5000，同时执行并发的请求是200
 *
 */
public class SemaphoreDemo2 {
    // 请求总数
    public static int clientTotal = 50000;
    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static int count = 0;


    public static void main(String[] args) throws InterruptedException {
        //通过Executors的方式创建线程池,不过生产不建议用这种方式创建线程池
     //   ExecutorService executorService = Executors.newCachedThreadPool();

        //信号量，此处用于控制并发的线程数
        final Semaphore semaphore = new Semaphore(threadTotal);

        //总请求数clientTotal为5000
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        SemaphoreDemo2 semaphoreDemo2=new SemaphoreDemo2();

         for (int i = 1; i <=clientTotal ; i++) {
             /*executorService.submit(()->{
                 System.out.println(Thread.currentThread().getName()+"\t"+"线程创建了");

                 try {
                     //1：申请信号量
                     semaphore.acquire();
                     //2:进行操作：
                     add();
                     //3:总请求减1
                     countDownLatch.countDown();
                 } catch (InterruptedException e) {

                 }finally {
                     semaphore.release();
                 }

             });*/


             new Thread(()->{
                 System.out.println(Thread.currentThread().getName()+"\t"+"线程创建了");
                 try {
                     //1：申请信号量
                     semaphore.acquire();
                     //2:进行操作：
                     semaphoreDemo2.add();
                     //3:总请求减1
                     countDownLatch.countDown();
                 } catch (InterruptedException e) {

                 }finally {
                     semaphore.release();
                 }
                 System.out.println(Thread.currentThread().getName()+"\t"+"执行完成了");
             },String.valueOf(i)).start();
         }

         //4:阻塞main线程,知道5000个请求全部处理完了之后再关闭线程池
        countDownLatch.await();
      //  executorService.shutdown();

        //5:查看线程操作后的结果：
        System.out.println("count:"+count);
    }

    private  void add() {
        count++;
    }

}
