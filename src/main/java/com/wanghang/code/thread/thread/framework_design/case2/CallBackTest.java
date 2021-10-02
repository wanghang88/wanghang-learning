package com.wanghang.code.thread.thread.framework_design.case2;


public class CallBackTest {


    public static void main(String[] args) {
        B b=new B();

        //A的操作：
        A a=new A(b);
        a.ask("1+1=?");
    }
}
