package com.wanghang.code.interFace;


public class UserServiceImpl implements IUserService {
    @Override
    public void save() {
        System.out.println("执行save()方法了");
    }

    @Override
    public String getStr(String str) {
        System.out.println("执行getStr()方法了");
        return str;
    }


    @Override
    public void saveLog() {
        System.out.println("执行最顶层的接口IBaseService的方法");
    }
}
