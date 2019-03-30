package com.wanghang.code.design.factory.factory1;


public class SimpleFactoryTest {
	
	public static void main(String[] args) {
		SimpleFactory simpleFactory=new SimpleFactory();
		Person person = (Person) simpleFactory.create(Person.class);
		person.createName("wanghang");
	}

}
