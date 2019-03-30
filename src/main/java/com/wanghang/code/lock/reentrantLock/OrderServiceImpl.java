package com.wanghang.code.lock.reentrantLock;

public class OrderServiceImpl implements OrderService {
	private static OrderCodeGenerator orderCodeGenerator=new OrderCodeGenerator();
	
	public String createOrderNum() {
		String orderNum = orderCodeGenerator.createOrderNum();
		System.out.println(Thread.currentThread().getName()+"============="+orderNum);
		return orderNum;
		
	}

}
