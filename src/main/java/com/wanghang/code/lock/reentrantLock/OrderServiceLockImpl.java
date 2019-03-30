package com.wanghang.code.lock.reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OrderServiceLockImpl implements OrderService {
	
	
    private OrderCodeGenerator orderCodeGenerator=new OrderCodeGenerator();
    private Lock lock=new ReentrantLock();
	
	public String createOrderNum() {
		try {
			lock.lock();
			String orderNum = orderCodeGenerator.createOrderNum();
			System.out.println(Thread.currentThread().getName()+"============="+orderNum);
			return orderNum;
		}finally {
			lock.unlock();	
		}
	}

}
