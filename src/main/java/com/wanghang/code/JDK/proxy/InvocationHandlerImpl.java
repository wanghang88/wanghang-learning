package com.wanghang.code.JDK.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * 调用处理器实现类:
 * 每次生成动态代理类对象时都需要指定一个实现了该接口的调用处理器对象
 */
public class InvocationHandlerImpl implements InvocationHandler {

    //需要代理的对象
    private Object subject;

    public InvocationHandlerImpl(Object subject) {
        this.subject = subject;
    }





    /**
     *该方法负责集中处理动态代理类上的所有方法调用，根据这三个参数进行预处理
     * @param proxy,   代理类实例
     * @param method   被调用的方法对象
     * @param args     调用参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //处理业务之前
        System.out.println("在调用之前，我要干点啥呢？");

        System.out.println("Method:" + method);

        Object returnValue = method.invoke(subject, args);

        //处理业务之后：
        System.out.println("在调用之后，我要干点啥呢？");

        return returnValue;
    }




    //生成代理对象：
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), subject.getClass().getInterfaces(), this);
    }
}
