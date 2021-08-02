package com.wanghang.code.algorithm.limit;


import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 *固定窗口时间算法
 *
 * 1秒钟5个请求的阀值
 */
public class FixedWindowsTryAcquire implements RateLimit {

    private Long lastRequestTime = System.currentTimeMillis();

    private final int maxVisitPerSecond;

    private static final int DEFAULT_ALLOWED_VISIT_PER_SECOND = 5;

    private AtomicInteger count;

    public FixedWindowsTryAcquire() {
        this(DEFAULT_ALLOWED_VISIT_PER_SECOND);
    }
    public FixedWindowsTryAcquire(int maxVisitPerSecond){
        this.maxVisitPerSecond = maxVisitPerSecond;
        this.count = new AtomicInteger();
    }

    //是否超过阀值(可以定义在接口中由子类来实现)
    public boolean isOverLimit() {
        return count.get() > maxVisitPerSecond;
    }
    //当前的流量,由AtomicInteger实现,不过这也可以定义在接口中,由实现类实现。
    public int currentQPS() {
        return count.get();
    }

    @Override
    public boolean execute() {
        long currentTime = System.currentTimeMillis();  //获取系统当前时间
        synchronized (lastRequestTime){
            if(currentTime-lastRequestTime>1000){
                lastRequestTime=currentTime;
                System.out.println(currentQPS());
                count.set(1);
                return false;
            }
            count.incrementAndGet();
            return true;
        }
    }

    //固定窗口限流算法测试:
    public static void main(String[] args) {
        RateLimit rateLimit = new FixedWindowsTryAcquire();
        new Thread(()->{
            while(true) {
                if(rateLimit.execute()){          //判断是否被限流
                    System.out.println("没有超过1秒内请求5个的阀值");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("超过1秒内请求5个的阀值");
                }
            }
        },"t1").start();
    }
}
