package com.wanghang.code.design.decorator;

import com.wanghang.code.design.decorator.one.ConcreteComponent.ConcreteComponent;
import com.wanghang.code.design.decorator.one.ConcreteDecorator.ConcreteDecorator;
import com.wanghang.code.design.decorator.one.component.Component;

public class DecoratorTest2 {
    public static void main(String[] args) {

        Component component = new ConcreteComponent();
        System.out.println("------装饰前：-------");
        component.function();


        Component newComponent = new ConcreteDecorator(component);
        System.out.println("------装饰后：-------");
        newComponent.function();
    }
}



