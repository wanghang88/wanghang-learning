package com.wanghang.code.design.responsibility.one;




public class SensitiveWordProcess implements Process {
    @Override
    public void doProcess(String msg) {
        System.out.println(msg + "敏感词处理");
    }
}
