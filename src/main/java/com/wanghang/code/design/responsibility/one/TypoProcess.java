package com.wanghang.code.design.responsibility.one;

public class TypoProcess implements Process {
    @Override
    public void doProcess(String msg) {
        System.out.println(msg + "其他处理");
    }
}
