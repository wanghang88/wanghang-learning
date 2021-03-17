package com.wanghang.code.JVM.oom;


/**
 *栈溢出：java.lang.StackOverflowError
 *      栈一般是512K，不断的深度调用，直到栈被撑破
 *案例：
 *    我们有最简单的一个递归调用，就会造成堆栈溢出，也就是深度的方法调用
 */
public class StackoverFlowErrorDemo {

    public static void main(String[] args) {
        stackOverflowError();
    }


    /**
     * 栈一般是512K，不断的深度调用，直到栈被撑破
     * Exception in thread "main" java.lang.StackOverflowError
     */
    private static void stackOverflowError() {
        stackOverflowError();
    }
}
