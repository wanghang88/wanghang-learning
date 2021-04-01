package com.wanghang.code.design.build.one.director;


import com.wanghang.code.design.build.one.concreteBuilder.IMobilePhoneBuilder;

// 这个是导演"Director"
public class Manufacturer {

    //指导创建Product产品的规范和创建流程
    public void construct(IMobilePhoneBuilder phoneBuilder) {
        phoneBuilder.buildBattery();
        phoneBuilder.buildOS();
        phoneBuilder.buildScreen();
        phoneBuilder.buildStylus();
    }
}
