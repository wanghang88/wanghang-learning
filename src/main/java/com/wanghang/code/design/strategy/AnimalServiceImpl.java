package com.wanghang.code.design.strategy;

public class AnimalServiceImpl implements Service{

	public void eat(String name) {
		System.out.println("动物吃的食物不是米饭,是骨头:"+name);
	}

	public void work(String work) {
		System.out.println("狗的工作是:"+work);
		
	}

}
