package com.wanghang.code.design.decorator.Decorator;

import com.wanghang.code.design.decorator.Component.BakeryComponent;



//Decorator :定义所有可动态添加功能的公共接口,引用Component对象,(BakeryComponent),  并且海还实现Component接口(BakeryComponent)
public abstract class Decorator implements BakeryComponent{

    private BakeryComponent baseComponent = null;


    protected String name = "Undefined Decorator";
    protected double price = 0.0;

    protected Decorator(BakeryComponent baseComponent) {
        this.baseComponent = baseComponent;
    }

    @Override
    public String getName() {
        return this.baseComponent.getName()+","+this.name;
    }

    @Override
    public double getPrice() {
        return this.baseComponent.getPrice()+this.price;
    }
}
