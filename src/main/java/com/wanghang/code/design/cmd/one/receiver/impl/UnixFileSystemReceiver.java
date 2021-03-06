package com.wanghang.code.design.cmd.one.receiver.impl;


import com.wanghang.code.design.cmd.one.receiver.FileSystemReceiver;

//Unix 系统操作文件的实现
public class UnixFileSystemReceiver implements FileSystemReceiver {

    @Override
    public void openFile() {
        System.out.println("Opening file in unix OS");
    }

    @Override
    public void writeFile() {
        System.out.println("Writing file in unix OS");
    }

    @Override
    public void closeFile() {
        System.out.println("Closing file in unix OS");
    }
}
