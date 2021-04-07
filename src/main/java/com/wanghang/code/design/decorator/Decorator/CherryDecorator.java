package com.wanghang.code.design.decorator.Decorator;

import com.wanghang.code.design.decorator.Component.BakeryComponent;

//Concrete Decorator
public class CherryDecorator extends Decorator {

    public CherryDecorator(BakeryComponent baseComponent) {
        super(baseComponent);
        this.name = "Cherry";
        this.price = 2.0;
    }

}
