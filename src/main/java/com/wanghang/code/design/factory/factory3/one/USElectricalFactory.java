package com.wanghang.code.design.factory.factory3.one;



//美国电器工厂创建美国的风扇和美国的日光灯
public class USElectricalFactory implements IElectricalFactory{

    public IFan createFan() {
        return new USFan();
    }

    public ITubeLight createTubeLight() {
        return new USTubeLight();
    }

}
