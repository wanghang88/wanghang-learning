package com.wanghang.code.design.factory.factory3.one;


//工厂的电器接口专门用来创建电风扇接口和日光灯接口
public interface IElectricalFactory {


    IFan createFan();

    ITubeLight createTubeLight();

}
