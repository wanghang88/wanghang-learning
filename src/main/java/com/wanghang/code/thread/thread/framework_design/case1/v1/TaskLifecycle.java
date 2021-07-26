package com.wanghang.code.thread.thread.framework_design.case1.v1;


/**
 * 定义线程生命周期的接口
 * @param <T>
 */
public interface TaskLifecycle<T> {

    //任务启动时会触发onStart方法
    void onStart(Thread thread);

    //任务正在运行会触发onRunning方法
    void onRunning(Thread thread);

    //任务执行结束时会触发的onFinish方法
    void onFinish(Thread thread);

    //任务执行报错会执行onError方法
    void onError(Thread thread);


    /**
     * 但是如果要实现不同的业务的刷，则只需要重新定义TaskLifecycle接口的实现就可以了。
     *
     *
     *
     * @param <T>
     */
    class EmptyLifecycle<T> implements  TaskLifecycle<T>{
        @Override
        public void onStart(Thread thread) {

        }
        @Override
        public void onRunning(Thread thread){

        }
        @Override
        public void onFinish(Thread thread){

        }
        @Override
        public void onError(Thread thread) {

        }
    }
}
