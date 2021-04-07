package com.wanghang.code.design.decorator;

import com.wanghang.code.design.decorator.one.ConcreteComponent.ConcreteComponent;
import com.wanghang.code.design.decorator.one.ConcreteDecorator.ConcreteDecorator;
import com.wanghang.code.design.decorator.one.component.Component;


/**
 *装饰器模式的具体运用：
 * https://blog.csdn.net/csdn15698845876/article/details/81544562
 */
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



