package com.wanghang.code.design.factory.factory3;

public class Factory3Test {
	
	
	
	/**
	 * 工厂模式之抽象工厂模式
	 *  1: 定义一个抽象工厂, 这个抽象工厂定义几个抽象的方法,用来创建具体业务service的抽象方法
	 *  2:定义一个工厂来继承这个抽象工厂, 这个工厂是用来创建业务service的实类， 但是返回的是业务service的接口
	 *  3:在业务Service, 这个来说的话是一个接口, 这个接口定义的是具体的业务方法;
	 *  4:业务service的实现类serviceImpl实现业务service的业务
	 *  5:使用具体的工厂对象,创建具体的业务service, 然后调用其方法
	 * @param args
	 */
	public static void main(String[] args) {
		Factory factory=new Factory();
		AnimalService animalService = factory.createAnimalService();
		animalService.eate("骨头");
		
		PersonService personService = factory.createPersonService();
		personService.createName("wanghang");
	}
	
	


}
