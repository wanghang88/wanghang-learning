package com.wanghang.code.design.factory.factory2.one;

public class PropellerFanFactory implements IFanFactory{
    @Override
    public IFan createFan() {
        return new PropellerFan();
    }
}
