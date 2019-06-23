package com.wanghang.code.design.responsibility.one;

public class CopyrightProcess implements Process {

    @Override
    public void doProcess(String msg) {
        System.out.println(msg + "版权处理");
    }
}
