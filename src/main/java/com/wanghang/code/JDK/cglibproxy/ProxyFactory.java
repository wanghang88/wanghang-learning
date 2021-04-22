package com.wanghang.code.JDK.cglibproxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 代理工厂类
 */
public class ProxyFactory implements MethodInterceptor {

    //真实的目标对象
    private Object target;
    public ProxyFactory(Object target) {
        this.target = target;
    }


   //1:普通类生成代理对象
    public Object getProxyInstrance() {
        //实例化Enhancer对象
        Enhancer enhancer = new Enhancer();
        //设置代理类拦截器 this指当前的Interceptor
        enhancer.setCallback(this);
        //如果目标对象是普通的类，这里就设置目标的对象为父类
        //设置当前的目标类为父类
        enhancer.setSuperclass(target.getClass());
        //如果目标对象实现了接口，我们要以接口去实现动态代理，就设置下面的接口参数
        //设置实现的接口 获取传入进来的对象的接口类
        //enhancer.setInterfaces(target.getClass().getInterfaces());
        //返回创建的对象
        return enhancer.create();
    }



    //2:生成接口的代理对象
    public Object getProxyInstrance1() {
        //实例化Enhancer对象
        Enhancer enhancer = new Enhancer();
        //设置代理类拦截器 this指当前的Interceptor
        enhancer.setCallback(this);
        //如果目标对象是普通的类，这里就设置目标的对象为父类
        //设置当前的目标类为父类
        enhancer.setSuperclass(target.getClass());
        //如果目标对象实现了接口，我们要以接口去实现动态代理，就设置下面的接口参数
        //设置实现的接口 获取传入进来的对象的接口类
        enhancer.setInterfaces(target.getClass().getInterfaces());
        //返回创建的对象
        return enhancer.create();
    }



    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("代码执行前增强！");
        Object returnObj;
        try {
            // 执行真实的目标对象的方法
            returnObj = method.invoke(target, args);
            System.out.println("代码执行后增强！");
        } catch (Exception e) {
            System.out.println("代码执行异常！");
            // 由于这里只是代理，所以对异常处理完之后，还需要将其再抛出去
            throw e;
        }
        System.out.println("代码执行完！");
        return returnObj;
    }
}
