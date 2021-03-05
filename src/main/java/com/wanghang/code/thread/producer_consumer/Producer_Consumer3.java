package com.wanghang.code.thread.producer_consumer;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class ShareData3{

    private volatile boolean FLAG=true;

    private BlockingQueue<Integer> blockingQueue=null;
    public ShareData3(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    private AtomicInteger atomicInteger=new AtomicInteger();

    public void put() throws Exception {
        // 多线程环境的判断，一定要使用while进行，防止出现虚假唤醒,当FLAG为true的时候，开始生产
        boolean reslutBool;
        Integer data=null;
        while (FLAG){
            data = atomicInteger.getAndIncrement();
            reslutBool = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
             if(reslutBool){
                 System.out.println(Thread.currentThread().getName()+"\t"+"放入的值:"+data+"成功");
             }else {
                 System.out.println(Thread.currentThread().getName()+"\t"+"放入的值:"+data+"失败");
             }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {

            }
        }
        System.out.println(Thread.currentThread().getName()+"\t"+"停止生产"+",表示FLAG为flase");
    }

    public void take() throws Exception {
        while (FLAG) {
            Integer reslut = blockingQueue.poll(2, TimeUnit.SECONDS);
            if (reslut == null) {
                System.out.println(Thread.currentThread().getName() + "\t" + "取出的值为失败");
                FLAG=false;
                return;
            } else {
                System.out.println(Thread.currentThread().getName() + "\t" + "取出的值为:" + reslut + "成功");
            }
        }
    }

    public void stop(){
        this.FLAG=false;
    }
}


/**
 *基于阻塞队列实现生产者和消费者的模型
 *
 */
public class Producer_Consumer3 {

    public static void main(String[] args) {

        ShareData3 shareData3=new ShareData3(new ArrayBlockingQueue(3));

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+"生产者线程启动");
            try {
                shareData3.put();
            } catch (Exception e) {

            }
        },"AA").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+"消费者线程启动");
            try {
                shareData3.take();
            } catch (Exception e) {

            }
        },"BB").start();


        //5秒钟之后，生产者和消费者都停
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {

        }
        System.out.println("");
        System.out.println("");
        System.out.println("5秒中后，生产和消费线程停止，线程结束");
        shareData3.stop();
    }

}
