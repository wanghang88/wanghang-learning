package com.wanghang.code.design.proxy;

import com.wanghang.code.design.proxy.Proxy.FolderProxy;

/**
 *代理模式：
 *Subject:定义接口;
 *RealSubject:subject定义接口的实现类;
 * Proxy:维护RealSubject的引用;
 *
 */
public class ProxyDesignDemo {
    
    public static void main(String[] args) {
        /**
         *1)FolderProxy代理类中的成员变量User,如果用户名和密码正确的话，则可以执行被代理的接口IFolder的的实现,在执行之前先执行代理的逻辑.
         *2)是基于FolderProxy类里的成员变量User做的对应的逻辑控制;
         */
        User user = new User("bobo", "xyz");
        FolderProxy folderProxy = new FolderProxy(user);
        System.out.println("When userName and password are correct:");
        folderProxy.performOperations();
        System.out.println("**************************************");
        // if we give wrong userName and Password
        User userWrong = new User("abc", "abc");
        FolderProxy folderProxyWrong = new FolderProxy(userWrong);
        System.out.println("When userName and password are incorrect");
        folderProxyWrong.performOperations();
    }
}
