package com.wanghang.code.design.factory.factory2.one;

public class FactoryMethodTest {

    public static void main(String[] args) {

        IFanFactory factory=new PropellerFanFactory();
        IFan fanService = factory.createFan();

        fanService.switchOff();
        fanService.swithOn();

    }
}
