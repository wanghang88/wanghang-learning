package com.wanghang.code.design.factory.factory3.one;



//中国电器工厂创建中国风扇和中国的日光灯
public class ChineseElectricalFactory implements IElectricalFactory{
    public IFan createFan() {
        return new ChineseFan();
    }

    public ITubeLight createTubeLight() {
        return new ChineseTubeLight();
    }
}
