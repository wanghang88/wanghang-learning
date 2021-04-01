package com.wanghang.code.design.cmd.one;

import com.wanghang.code.design.cmd.one.command.impl.CloseFileCommand;
import com.wanghang.code.design.cmd.one.command.impl.OpenFileCommand;
import com.wanghang.code.design.cmd.one.command.impl.WriteFileCommand;
import com.wanghang.code.design.cmd.one.invoker.FileInvoker;
import com.wanghang.code.design.cmd.one.receiver.FileSystemReceiver;
import com.wanghang.code.design.cmd.one.receiver.FileSystemReceiverUtil;
import com.wanghang.code.design.cmd.one.receiver.impl.FileSystemReceiverImpl;

public class CommandDesignDemo {

    public static void main(String[] args) {
        CommandDesignDemo commandDesignDemo=new CommandDesignDemo();
        //1:接收者有多种实现的情况下:
        commandDesignDemo.receiverMore();

        //2:接收者处理对象只有一种实现的情况下:
        commandDesignDemo.receiverOne();

    }

    private  void receiverMore() {
        //1;创建命令的接受者对象,FileSystemReceiverUtil类中的代码System.getProperty("os.name"),可以知道系统的类型从而选择对应的接收者处理类
        FileSystemReceiver fs= FileSystemReceiverUtil.getUnderlyingFileSystem();

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


        //测试java定义的一些变量:
        System.out.println("===============================");
        String userDir = System.getProperty("user.dir");                    //user.dir用户的当前工作目录;
        String userHome = System.getProperty("user.home");                 //user.home用户的主目录;
        String lineSeparator = System.getProperty("line.separator");       //行分割符号;
        String fileSeparator = System.getProperty("file.separator");       //文件分隔符（在 UNIX 系统中是“/”);
        String javaHome = System.getProperty("java.home");                 //Java的安装目录;
        String javaClassPath  = System.getProperty("java.class.path");     //Java 类路径



        System.out.println("userDir:"+userDir);
        System.out.println("userHome:"+userHome);
        System.out.println("lineSeparator:"+lineSeparator);
        System.out.println("fileSeparator:"+fileSeparator);
        System.out.println("javaHome:"+javaHome);
        System.out.println("javaClassPath:"+javaClassPath);
    }

    private  void receiverOne() {
        //1;创建命令的接受者对象,接收处理对象只有一种实现的情况下;
        FileSystemReceiver fs= new FileSystemReceiverImpl();
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
