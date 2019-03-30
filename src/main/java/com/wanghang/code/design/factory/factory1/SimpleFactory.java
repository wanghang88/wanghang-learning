package com.wanghang.code.design.factory.factory1;

/**
 * 工厂模式之简单工厂
 * 
 * @author wangh
 *
 */
public class SimpleFactory {
	public Object create(Class<?> clazz) {
        if (clazz.getName().equals(Person.class.getName())) {
            return createPerson();
        } else if (clazz.getName().equals(Animal.class.getName())) {
            return createBroom();
        }
        return null;
    }
	
	private Person createPerson() {
        return new Person();
    }
    
    private Animal createBroom() {
        return new Animal();
    }
}
