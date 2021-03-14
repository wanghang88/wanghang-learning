package com.wanghang.code.design.factory.factory3.one;

public class AbstractFactoryTest {

    public static void main(String[] args) {

        // 国产
        IElectricalFactory electricalFactory = new ChineseElectricalFactory();
        IFan chineseFanService = electricalFactory.createFan();
        ITubeLight chineseTubeLightService = electricalFactory.createTubeLight();

        chineseFanService.switchOff();
        chineseTubeLightService.tuneLight();

        electricalFactory=new USElectricalFactory();
        IFan uSFanService = electricalFactory.createFan();
        ITubeLight uSTubeLightService = electricalFactory.createTubeLight();

        uSFanService.switchOff();
        uSTubeLightService.tuneLight();
    }
}
