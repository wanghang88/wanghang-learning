package com.wanghang.code.design.decorator.one.Decorator;

import com.wanghang.code.design.decorator.one.component.Component;

public class Decorator implements Component {

    private Component component; //持有一个Component类型的对象引用


    public Decorator(Component component) {
        this.component = component;
    }


        @Override
    public void function() {
        component.function(); //客户端的调用委派给具体的子类
    }

}
