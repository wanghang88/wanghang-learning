package com.wanghang.code.design.factory.factory2.one;

public class TableFanFactory implements IFanFactory{

    @Override
    public IFan createFan() {
        return new TableFan();
    }
}
