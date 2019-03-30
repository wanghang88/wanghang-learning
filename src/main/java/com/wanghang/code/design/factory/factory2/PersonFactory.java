package com.wanghang.code.design.factory.factory2;

public class PersonFactory extends Factory{
	@Override
	public Moveable create() {
		return new Person();
	}

}
