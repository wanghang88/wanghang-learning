package com.wanghang.code.design.factory.factory1;


/**
 *刷设计模式的
 * https://github.com/wanghang88/core-spring-patterns
 */
public class SimpleFactoryTest {
	
	public static void main(String[] args) {
		SimpleFactory simpleFactory=new SimpleFactory();
		Person person = (Person) simpleFactory.create(Person.class);
		person.createName("wanghang");
	}

}
