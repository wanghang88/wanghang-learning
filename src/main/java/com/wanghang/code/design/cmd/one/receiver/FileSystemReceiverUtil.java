package com.wanghang.code.design.cmd.one.receiver;


import com.wanghang.code.design.cmd.one.receiver.FileSystemReceiver;
import com.wanghang.code.design.cmd.one.receiver.impl.UnixFileSystemReceiver;
import com.wanghang.code.design.cmd.one.receiver.impl.WindowsFileSystemReceiver;

//获取具体哪一个文件的操作类：类似于简单工厂,可以结合简单工厂一起使用
public class FileSystemReceiverUtil {

    public static FileSystemReceiver getUnderlyingFileSystem() {
        String osName = System.getProperty("os.name");
        System.out.println("Underlying OS is : " + osName);
        if (osName.contains("Windows")) {
            return new WindowsFileSystemReceiver();
        } else {
            return new UnixFileSystemReceiver();
        }
    }
}
