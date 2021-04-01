package com.wanghang.code.design.build.one.concreteBuilder;


import com.wanghang.code.design.build.one.product.MobilePhone;

// 这个是构建者"Builder"接口
public interface IMobilePhoneBuilder {

    void buildScreen();

    void buildBattery();

    void buildOS();

    void buildStylus();

    MobilePhone getPhone();
}
