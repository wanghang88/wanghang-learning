package com.wanghang.code.design.strategy;



/**
 * 策略模式
 * 
 * 定义:同一个问题有多个不能的解决方式,也就是说提供了多种不同的解决策略,或者同一组业务有不同的解决方案(比如: 多个对象有相同的一组行为,接单, 拒单， 取消订单等等);
 * 
 * 1：定义一个接口， 这个接口中定义,这组接口相同的方法(接单,拒单,取消订单等等)
 * 2：各自不不同的业务对象实现来实现这个接口,
 * 3：定义一个策略工厂, 这个工厂有一个成员变量就是上面定义的接口, 并且有一个构造方法, 参数就是这个接口类,并且在这个策略工厂中将这些接口中定义的方法根据service调用给执行
 * 4:使用时,获取这个策略工厂对象就可以了, 然后调用里面的方法就可以获取相应的返回值
 * 
 * @author wangh
 *
 */

public class StrategyFactory {
	
    private Service service;                //接口

	public StrategyFactory(Service service) {
        this.service = service;
    }

    public void eate(String name) {
	    service.eat(name);
    }
    
    
    public void work(String work) {
    	service.work(work);
    }
}
	