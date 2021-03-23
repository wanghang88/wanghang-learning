package com.wanghang.code.design.decorator.Decorator;

import com.wanghang.code.design.decorator.BakeryComponent;

public class CreamDecorator extends Decorator {

    public CreamDecorator(BakeryComponent baseComponent) {
        super(baseComponent);
        this.name = "Cream";
        this.price = 1.0;
    }

}
