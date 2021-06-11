package com.wanghang.code.thread.threadlocal;


/**
 *阿里开源transmittable-thread-local
 *
 *JDK的InheritableThreadLocal类可以完成父线程到子线程的值传递,
 * 但对于使用线程池等会池化复用线程的执行组件的情况,线程由线程池创建好,并且线程是池化起来反复使用的,使用InheritableThreadLocal就行不通了。
 * 阿里TransmittableThreadLocal类继承并加强InheritableThreadLocal类，解决上述的问题。
 *
 * gitHub地址：
 * https://github.com/alibaba/transmittable-thread-local
 *
 *todo：有时间再好好地研究下
 *
 *
 */
public class ThreadLocalDemo5 {

    public static void main(String[] args) {


    }
}
