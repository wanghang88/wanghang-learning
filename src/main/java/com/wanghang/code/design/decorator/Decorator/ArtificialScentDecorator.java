package com.wanghang.code.design.decorator.Decorator;

import com.wanghang.code.design.decorator.BakeryComponent;

//Concrete Decorator
public class ArtificialScentDecorator extends Decorator {

    public ArtificialScentDecorator(BakeryComponent baseComponent) {
        super(baseComponent);
        this.name = "Artificial Scent";
        this.price = 3.0;
    }
}
