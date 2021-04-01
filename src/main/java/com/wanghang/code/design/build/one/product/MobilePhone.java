package com.wanghang.code.design.build.one.product;


import com.wanghang.code.design.build.one.Battery;
import com.wanghang.code.design.build.one.OperatingSystem;
import com.wanghang.code.design.build.one.ScreenType;
import com.wanghang.code.design.build.one.Stylus;
import lombok.Data;

/**
 * 组装出来的最后产品
 */
@Data
public class MobilePhone {
    // 部件类型
    private String phoneName;
    private ScreenType phoneScreen;
    private Battery phoneBattery;
    private OperatingSystem phoneOS;
    private Stylus phoneStylus;


    // region 访问手机组件的getter/setter公开方法
    public MobilePhone(String phoneName) {
        this.phoneName = phoneName;
    }


    @Override
    public String toString() {
        return String.format("Name: %s\nScreen: %s\nBattery: %s\nOS: %s\nStylus: %s\n", this.phoneName, this.phoneScreen, this.phoneBattery, this.phoneOS, this.phoneStylus);
    }
}
