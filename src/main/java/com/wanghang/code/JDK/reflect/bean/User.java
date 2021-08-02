package com.wanghang.code.JDK.reflect.bean;

import com.wanghang.code.JDK.reflect.annotation.MyAnnotation;

public class User extends Person{

    public String name;
    private int age;

    public User() {
        super();
    }
    public User(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }
    private User(int age){
        super();
        this.age = age;
    }
    public User(String name){
        super();
        this.name = name;
    }



    @Override
    public String toString() {
        return "User [name=" + name + ", age=" + age + "]";
    }


    //退出
    public void exit(){
        System.out.println(name+"退出");
    }

    //登陆
    public void login(String username,String password){
        System.out.println("用户名:"+username);
        System.out.println("密码:"+password);
    }

    //校验：
    private String CheckInfo(){
        return "年龄:"+age;
    }


    public String getUserName(String name){
        return name;
    }


    @MyAnnotation(age = 100,name = "wanghang")
    public String getUserNameAndAgeByAnnotation(){
        return this.name+this.age;
    }
}
