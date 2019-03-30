package com.wanghang.code.design.factory.factory1;




public class Person {
	private String name;
	
	private int age;
	
	private String address;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
   public void createName(String name) {
	   System.out.println("人的名字是:"+name);
   }
}
