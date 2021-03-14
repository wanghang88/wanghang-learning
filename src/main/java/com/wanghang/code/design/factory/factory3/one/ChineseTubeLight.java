package com.wanghang.code.design.factory.factory3.one;


//中国日光灯接口的实现
public class ChineseTubeLight implements ITubeLight{

    public void swithOn() {
        System.out.println("The ChineseTubeLight is swithed on ...");
    }

    public void switchOff() {
        System.out.println("The ChineseTubeLight is swithed off ...");
    }

    public void tuneLight() {
        System.out.println("The ChineseTubeLight is tuned ...");

    }
}
