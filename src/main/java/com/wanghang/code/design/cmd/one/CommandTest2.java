package com.wanghang.code.design.cmd.one;

import jdk.internal.org.objectweb.asm.tree.MultiANewArrayInsnNode;

public class CommandTest2 {

    public static void main(String[] args) {
        //根据utils具体获取哪个执行的对象
        FileSystemReceiver fs = FileSystemReceiverUtil.getUnderlyingFileSystem();
        OpenFileCommand openFileCommand = new OpenFileCommand(fs);
        FileInvoker file = new FileInvoker(openFileCommand);
        file.execute();

        WriteFileCommand writeFileCommand = new WriteFileCommand(fs);
        file = new FileInvoker(writeFileCommand);
        file.execute();

        CloseFileCommand closeFileCommand = new CloseFileCommand(fs);
        file = new FileInvoker(closeFileCommand);
        file.execute();
    }


















}
