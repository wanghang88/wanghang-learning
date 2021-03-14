package com.wanghang.code.design.factory.factory3.one;



//美国电风扇接口的实现
public class USFan implements IFan{

    public void swithOn() {
        System.out.println("The USFan is swithed on ...");
    }

    public void switchOff() {
        System.out.println("The USFan is swithed off ...");
    }

}
