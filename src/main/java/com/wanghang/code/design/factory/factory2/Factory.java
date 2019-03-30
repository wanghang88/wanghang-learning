package com.wanghang.code.design.factory.factory2;


/**
 * 
 * 工厂模式之:工厂方法模式
 * 1：定义一个抽象工厂, 这个抽象工厂定义一个创建对象的抽象方法
 * 2：定义一个具体的工厂，用来负责创建对象,返回这个这个对象实现的接口
 * 3：定义一个接口(接口中定义方法)
 * 4：创建一个类,这个类实现接口
 * 
 * @author wangh
 *
 */
public abstract class Factory {
	 public abstract Moveable create();
}
