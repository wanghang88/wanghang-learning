package com.wanghang.code.design.proxy.Proxy;

import com.wanghang.code.design.proxy.User;
import com.wanghang.code.design.proxy.realSubject.Folder;
import com.wanghang.code.design.proxy.suject.IFolder;


//FolderProxy 代理类代理IFolder接口
public class FolderProxy implements IFolder {

    Folder folder;
    User user;

    public FolderProxy(User user) {
        this.user = user;
    }

    public void performOperations() {
        if (user.getUserName().equalsIgnoreCase("bobo") && user.getPassword().equalsIgnoreCase("xyz")) {
            folder = new Folder();
            folder.performOperations();
        } else {
            System.out.println("You don't have access to this folder");
        }
    }
}
