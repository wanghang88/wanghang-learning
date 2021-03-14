package com.wanghang.code.design.factory.factory2.one;


//螺旋扇
public class PropellerFan implements IFan{

    @Override
    public void swithOn() {
        System.out.println("The PropellerFan is swithed on ...");
    }

    @Override
    public void switchOff() {
        System.out.println("The PropellerFan is swithed off ...");
    }
}
