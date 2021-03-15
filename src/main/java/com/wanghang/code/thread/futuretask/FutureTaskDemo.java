package com.wanghang.code.thread.futuretask;


import com.wanghang.code.utils.DateUtil;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 *java获取线程的方式：
 *  实现Runnable接口
 *  实现Callable接口
 *  实例化Thread类
 *  使用线程池获取
 *Callable：
 *        Callable接口，是一种让线程执行完成后，能够返回结果的(场景:一般就在于批处理业务，比如转账的时候，需要给一会返回结果的状态码回来，代表本次操作成功还是失败)
 *
 * 1)通过Thread线程， 将MyThread2实现Callable接口的类包装起来;
 * 2)这里需要用到的是FutureTask类(他实现了Runnable接口，并且还需要传递一个实现Callable接口的类作为构造函数), FutureTask的构造函数需要Callable(new FutureTask<>(new MyThread2()));
 * 3)构建线程的实例new Thread(Runnable,""),由于FutureTask是实现Runnable接口的,所以可以把FutureTask传入进去;
 * 4)最后通过 futureTask.get() 获取到返回值,要求获得Callable线程的计算结果，如果没有计算完成就要去强求，会导致阻塞，直到计算完成;
 *          所以:futureTask.get() 需要放在最后执行，这样不会导致主线程阻塞;
 *          也可以使用下面算法，使用类似于自旋锁的方式来进行判断是否运行完毕
 *          while(!futureTask.isDone()) {
 *          }
 */

class MyThread2 implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("come in Callable:"+ DateUtil.format(new Date(),DateUtil.YMDHMS));
        TimeUnit.SECONDS.sleep(1);
        return 1024;
    }
}


public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());
        Thread t1 = new Thread(futureTask, "aaa");
        t1.start();
        while (!futureTask.isDone()){  //这样自旋会造成cpu的浪费
            System.out.println("尝试获取线程的结果");
        }
        System.out.println("futureTask get获取到结果的时间"+ DateUtil.format(new Date(),DateUtil.YMDHMS));
        System.out.println("result FutureTask " + futureTask.get());
    }

}
