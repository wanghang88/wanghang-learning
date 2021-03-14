package com.wanghang.code.design.factory.factory1.one;

public class FanFactoryTest {

    public static void main(String[] args) {
        IFanFactory simpleFactory = new FanFactory();
        // 使用简单工厂创建一个电扇
        IFan fan = simpleFactory.createFan(FanType.TableFan);
        fan.swithOn();
        fan.switchOff();
    }
}
