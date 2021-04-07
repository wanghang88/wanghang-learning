package com.wanghang.code.design.decorator.one.ConcreteDecorator;

import com.wanghang.code.design.decorator.Component.BakeryComponent;
import com.wanghang.code.design.decorator.one.Decorator.Decorator;
import com.wanghang.code.design.decorator.one.component.Component;


/**
 * 具体装饰角色ConcreteDecorator,其实是Decorator的实现类
 */
public class ConcreteDecorator extends Decorator {
    public ConcreteDecorator(Component component) {
        super(component);
    }
    @Override
    public void function() {
        super.function();
        System.out.println("附加功能：");

        //通过装饰者模式,新加的功能
        this.eat();
        this.bellow();

    }
    private void eat() {
        System.out.println("吃肉");
    }
    private void bellow() {
        System.out.println("吼叫");
    }
}
