package com.wanghang.code.interFace;


public class BaseServiceImpl implements IBaseService {
    @Override
    public void save() {
        System.out.println("执行save()方法了");
    }

    @Override
    public String getStr(String str) {
        System.out.println("执行getStr()方法了");
        return str;
    }
}
