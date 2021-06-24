package com.wanghang.code.thread.completionService;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 *CompletionService
 * 参考博文:
 *        https://blog.csdn.net/qq_36898043/article/details/79733124
 *
 *CompletionService与ExecutorService,类似都可以用来执行线程池的任务,
 *
 *
 */

public class CompletionServiceDemo {

    public static void main(String[] args) {
        final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        CompletionService<String> completionService = new ExecutorCompletionService<String>(Executors.newFixedThreadPool(10));

        //1：此线程池运行5个线程
        for (int i = 0; i < 5; i++) {
            final int index = i;
            completionService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println("Thread-" + index + "-begin-" + sf.format(new Date()));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread-" + index + "-end-" + sf.format(new Date()));
                    return "index-" + index;
                }

            });
        }

        //2：获取线程池执行线程的结果
        try {
            Future<String> future = completionService.poll(); // poll方法 返回的Future可能为 null，因为poll 是非阻塞执行的
            if (future != null) {
                System.out.println(future.get());
            } else {
                System.out.println("使用poll 获取到的Future为 null");
            }
        } catch (Exception e1) {

        }

        //队列的take()方法
        for (int i = 0; i < 5; i++) {
            try {
                // csv.take() 返回的是 最先完成任务的 Future 对象，take 方法时阻塞执行的
                System.out.println(completionService.take().get());
            } catch (Exception e) {

            }
        }
    }
}
