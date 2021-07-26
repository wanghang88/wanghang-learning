package com.wanghang.code.thread.thread.framework_design.case1;


public interface Task<T> {

    //任务执行的接口
    T call();
}
