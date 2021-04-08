package com.wanghang.code.JDK.proxy;


//需要代理的实际对象
public class UserviceImpl implements IUservice {

    @Override
    public String SayHello(String name) {
        return "hello " + name;
    }
    
    @Override
    public String SayGoodBye() {
        return " good bye ";
    }
}
