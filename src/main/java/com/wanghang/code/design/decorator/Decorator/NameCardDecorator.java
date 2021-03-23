package com.wanghang.code.design.decorator.Decorator;

import com.wanghang.code.design.decorator.BakeryComponent;

public  class NameCardDecorator extends Decorator {

    public NameCardDecorator(BakeryComponent baseComponent) {
        super(baseComponent);
        this.name = "Name Card";
        this.price = 4.0;
    }

    @Override
    public String getName() {
        return super.getName() + "(Please Collect your discount card for " + this.price + ")";
    }
}
