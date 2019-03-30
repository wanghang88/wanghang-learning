package com.wanghang.code.design.factory.factory2;

public class Person implements Moveable {

	public void createName(String name) {
			System.out.println("通过抽象工厂创建出来的对象只想创建名字的是:"+name);
	}

}
