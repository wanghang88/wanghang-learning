package com.wanghang.code.design.build.one.concreteBuilder;


import com.wanghang.code.design.build.one.Battery;
import com.wanghang.code.design.build.one.OperatingSystem;
import com.wanghang.code.design.build.one.ScreenType;
import com.wanghang.code.design.build.one.Stylus;
import com.wanghang.code.design.build.one.product.MobilePhone;

// 安卓手机具体构建者"ConcreteBuilder"
public class AndroidPhoneBuilder implements IMobilePhoneBuilder {
    private MobilePhone phone;
    public AndroidPhoneBuilder() {
        this.phone = new MobilePhone("Android Phone");
    }

    /**
     *安卓手机设置屏幕类型;
     */
    @Override
    public void buildScreen() {
        phone.setPhoneScreen(ScreenType.SCREENTYPE_TOUCH_RESISTIVE);
    }


    /**
     * 安卓手机设置电池;
     */
    @Override
    public void buildBattery() {
        phone.setPhoneBattery(Battery.MAH_1500);
    }

    /**
     * 安卓手机的操作系统;
     */
    @Override
    public void buildOS() {
        phone.setPhoneOS(OperatingSystem.ANDROID);
    }

    /**
     * 安卓手机是否为触控笔
     */
    @Override
    public void buildStylus() {
        phone.setPhoneStylus(Stylus.YES);
    }


    // 获得最终构建出来的产品
    @Override
    public MobilePhone getPhone() {
        return this.phone;
    }
}
