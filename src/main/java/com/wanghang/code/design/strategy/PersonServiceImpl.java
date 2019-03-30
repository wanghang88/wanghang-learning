package com.wanghang.code.design.strategy;



public class PersonServiceImpl implements Service{

	public void eat(String name) {
		System.out.println("人吃的事务是米饭:"+name);
	}

	public void work(String work) {
		System.out.println("人的工作是"+work);
	}

}
