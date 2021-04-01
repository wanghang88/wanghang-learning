package com.wanghang.code.design.cmd.one.receiver.impl;

import com.wanghang.code.design.cmd.one.receiver.FileSystemReceiver;

public class FileSystemReceiverImpl implements FileSystemReceiver {
    @Override
    public void openFile() {
       System.out.println("FileSystemReceiver 的实现类FileSystemReceiverImpl 实现openFile()方法");
    }
    @Override
    public void writeFile() {
        System.out.println("FileSystemReceiver 的实现类FileSystemReceiverImpl 实现writeFile()方法");
    }
    @Override
    public void closeFile() {
        System.out.println("FileSystemReceiver 的实现类FileSystemReceiverImpl 实现closeFile()方法");
    }
}
