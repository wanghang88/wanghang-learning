package com.wanghang.code.thread.threadpool.cas1;


/**
 *场景：
 *    将用户提交的任务Job交由线程池去统一处理，提交系统的处理能力;
 */
public class DefaultThreadPoolTest {

    public static void main(String[] args) {
        DefaultThreadPool defaultThreadPool=new DefaultThreadPool();

        //1:添加job
        Job job=null;
         for (int i = 0; i <30 ; i++) {
             job=new Job(i,i+"job");
             defaultThreadPool.execute(job);
         }

         //2:开启线程来消费job
         defaultThreadPool.addWorkers(3);


         System.out.println("30个job全部都处理完了");
    }
}
