package com.wanghang.code.design.factory.factory3.one;


//美国日光灯接口的实现
public class USTubeLight implements ITubeLight{

    public void swithOn() {
        System.out.println("The USTubeLight is swithed on ...");
    }

    public void switchOff() {
        System.out.println("The USTubeLight is swithed off ...");
    }

    public void tuneLight() {
        System.out.println("The USTubeLight is tuned ...");
    }
}
