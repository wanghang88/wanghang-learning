package com.wanghang.code.design.build.one.concreteBuilder;

import com.wanghang.code.design.build.one.Battery;
import com.wanghang.code.design.build.one.OperatingSystem;
import com.wanghang.code.design.build.one.ScreenType;
import com.wanghang.code.design.build.one.Stylus;
import com.wanghang.code.design.build.one.product.MobilePhone;

//WindowsPhone手机的Builder
public class WindowsPhoneBuilder implements IMobilePhoneBuilder {
    private MobilePhone phone;
    public WindowsPhoneBuilder() {
        this.phone = new MobilePhone("Windows Phone");
    }


    /**
     * 设置屏幕类型;
     */
    @Override
    public void buildScreen() {
        phone.setPhoneScreen(ScreenType.SCREENTYPE_TOUCH_CAPACITIVE);
    }


    /**
     * 设置电池：
     */
    @Override
    public void buildBattery() {
        phone.setPhoneBattery(Battery.MAH_2000);
    }


    /**
     * 配置操作系统
     */
    @Override
    public void buildOS() {
        phone.setPhoneOS(OperatingSystem.WINDOWS_PHONE);
    }


    /**
     * 设置是否为触控笔
     */
    @Override
    public void buildStylus() {
        phone.setPhoneStylus(Stylus.NO);
    }


    @Override
    public MobilePhone getPhone() {
        return this.phone;
    }
}
