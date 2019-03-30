package com.wanghang.code.design.factory.factory2;




public class FactoryTest2 {
   
	
	public static void main(String[] args) {
		Factory factory=new PersonFactory();
		Moveable moveable = factory.create();
		moveable.createName("wanghang");
	}

}
