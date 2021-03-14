package com.wanghang.code.design.factory.factory2.one;

public class ExhaustFanFactory implements IFanFactory{

    @Override
    public IFan createFan() {
        return new ExhaustFan();
    }
}
