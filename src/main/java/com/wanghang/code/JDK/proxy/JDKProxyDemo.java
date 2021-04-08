package com.wanghang.code.JDK.proxy;

/**
 *基于JDK实现动态代理：
 * a)Java动态代理类位于java.lang.reflect包下，一般主要涉及到以下两个类
 *    Interface InvocationHandler,这个接口定义了一个方法：public object invoke(Object obj,Method method, Object[] args)
      这个方法的三个参数：
      第一个参数obj一般是指代理类，
      method是被代理的方法，
      args为该方法的参数数组
 *
 *b)Proxy：该类即为动态代理类
 *
 *2:如何实现JDK的动态代理：
 *2.1)创建一个实现接口InvocationHandler的类，它必须实现invoke方法(本案例中的InvocationHandlerImpl,它又一个成员变需要代理的接口)
 *2.2)创建被代理的类以及接口(本案例中的IUservice接口)
 *2.3)通过Proxy的静态方法,newProxyInstance(ClassLoaderloader, Class[] interfaces, InvocationHandler h),
 *              创建一个代理(本案例中创建的IUservice的代理对象)
 *2.4):通过代理调用方法:
 *
 *
 *3:JDK动态代理的特点：
 *    运行阶段进行代理，通用性强，常见：JDK动态代理和CGLIB动态代理
 *
 *    JDK动态代理;
 *    JDK动态代理的被代理类必须是某个接口的实现，而CGLIB不用必须实现接口
 *    JDK动态代理是jdk自带代理，可直接使用；使用CGLIB代理需要额外引入CGLIB库，甚至要在配置文件中配置
 *
 *    CGlib动态代理：
 *    实现MethodInterceptor 重写intercept (methodProxy.invokeSuper(Object,args))
 *    完全不受代理类必须实现接口类的限制,被代理的类不能申明为final,并且生成的代理类是原来类的子类
 *
 *4:关于spring如何使用动态代理：
 *    4.1)可以任意的控制任意对象的执行过程，这个对象的执行过程可以由客户端灵活的指定,
 *
 *
 */
public class JDKProxyDemo {
    public static void main(String[] args) {
        IUservice uservice=new UserviceImpl();

        //1:生成IUservice的代理对象:
        InvocationHandlerImpl handler = new InvocationHandlerImpl(uservice);
        IUservice proxy = (IUservice) handler.getProxy();

        //2:代理对象执行sayHello方法演示:
        String sayHelloReslut = proxy.SayHello("汪行");
        System.out.println("代理对象执行sayHello方法的结果:"+sayHelloReslut);

        //3:代理对象执行再见方法演示:
        String sayGoodByeReslut = proxy.SayGoodBye();
        System.out.println("代理对象执行SayGoodBye方法的结果:"+sayGoodByeReslut);
    }
}
