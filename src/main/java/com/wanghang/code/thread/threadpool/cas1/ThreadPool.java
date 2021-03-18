package com.wanghang.code.thread.threadpool.cas1;

public interface ThreadPool<T extends Runnable>{
    //1：执行一个任务
    void execute(Job job);

    //2:关闭线程池
    void shutDown();

    //3：添加任务
    void addWorkers(int num);

    //4:删除任务
    void removeWorkers(int num);

    //5:获取正在等待执行线程的个数
    int getJobSize();
}
