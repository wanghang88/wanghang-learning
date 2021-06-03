package com.wanghang.code.interFace;

public class UserServiceDemo {

    public static void main(String[] args) {
        IUserService userService=new UserServiceImpl();
        userService.getStr("wanghang");
        userService.saveLog();
    }
}
