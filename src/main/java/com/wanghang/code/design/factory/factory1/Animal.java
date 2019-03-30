package com.wanghang.code.design.factory.factory1;

public class Animal {
	private String name;
	
	private int age;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public void createName(String name) {
		   System.out.println("动物的名字是:"+name);
	   }
}
