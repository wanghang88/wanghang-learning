package com.wanghang.code.javasystemenvironment;

public class JavaSystemEnvironment {


    public static void main(String[] args) {
        System.out.println("java版本号：" + System.getProperty("java.version"));    // java版本号
        System.out.println("当前程序所在目录：" + System.getProperty("user.dir"));    // 当前程序所在目录
        System.out.println("操作系统用户的主目录：" + System.getProperty("user.home")); // 用户的主目录
        System.out.println("操作系统用户名：" + System.getProperty("user.name"));     // 用户名
        System.out.println("操作系统名称：" + System.getProperty("os.name"));         // 操作系统名称
        System.out.println("文件分隔符：" + System.getProperty("file.separator"));    // 文件分隔符
        System.out.println("直线分隔符：" + System.getProperty("line.separator"));    // 直线分隔符
        System.out.println("路径分隔符：" + System.getProperty("path.separator")); // 路径分隔符
        System.out.println("jre目录：" + System.getProperty("java.home")); // Java，哦，应该是jre目录
        System.out.println("Java类路径：" + System.getProperty("java.class.path")); // Java类路径
        System.out.println("Java虚拟机版本号：" + System.getProperty("java.vm.version")); // Java虚拟机版本号

    }
}
