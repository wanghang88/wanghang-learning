package com.wanghang.code.design.factory.factory2.one;

public class CeilingFanFactory implements IFanFactory{

    @Override
    public IFan createFan() {
        return new CeilingFan();
    }
}
