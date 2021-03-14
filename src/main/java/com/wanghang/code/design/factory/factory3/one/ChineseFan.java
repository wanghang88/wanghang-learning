package com.wanghang.code.design.factory.factory3.one;



//中国电风扇接口的实现
public class ChineseFan implements IFan{
    public void swithOn() {
        System.out.println("The ChineseFan is swithed on ...");
    }

    public void switchOff() {
        System.out.println("The ChineseFan is swithed off ...");
    }
}
