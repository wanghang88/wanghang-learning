package com.wanghang.code.design.proxy.realSubject;

import com.wanghang.code.design.proxy.suject.IFolder;


// RealSubject:subject包下的接口IFolder接口的实现类
public class Folder implements IFolder {
    public void performOperations() {
        System.out.println("Performing operation on folder");
    }
}
