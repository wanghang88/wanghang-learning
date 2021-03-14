package com.wanghang.code.design.cmd.one;

public class CommandTest {


    public static void main(String[] args) {
        //1;创建命令的接受者对象;
        FileSystemReceiver fs=new FileSystemReceiverImpl();
        //2:创建命令的执行对象;
        OpenFileCommand openFileCommand = new OpenFileCommand(fs);
        WriteFileCommand writeFileCommand = new WriteFileCommand(fs);
        CloseFileCommand closeFileCommand = new CloseFileCommand(fs);

        //3：执行命令;
        FileInvoker file = new FileInvoker(openFileCommand);
        file.execute();

        file=new FileInvoker(writeFileCommand);
        file.execute();

        file=new FileInvoker(closeFileCommand);
        file.execute();
    }
}
