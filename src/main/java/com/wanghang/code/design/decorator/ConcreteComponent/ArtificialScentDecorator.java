package com.wanghang.code.design.decorator.ConcreteComponent;

import com.wanghang.code.design.decorator.Component.BakeryComponent;
import com.wanghang.code.design.decorator.Decorator.Decorator;

//ConcreteDecorator：待装饰的实际对象
public class ArtificialScentDecorator extends Decorator {

    public ArtificialScentDecorator(BakeryComponent baseComponent) {
        super(baseComponent);
        this.name = "Artificial Scent";
        this.price = 3.0;
    }
}
