package com.wanghang.code.thread.thread.framework_design.case1.v1.observe;

public interface Observable {

    enum Cycle{
        START,RUNNING,DONE,ERROR
    }

    //获取当前任务的生命周期状态
    Cycle getCycle();

    //自定义线程启动的方法
    void start();

    //自定义线程打断的方法
    void interrupt();
}
