package com.wanghang.code.design.cmd.one.command.impl;


import com.wanghang.code.design.cmd.one.command.Command;
import com.wanghang.code.design.cmd.one.receiver.FileSystemReceiver;

//写文件的命令
public class WriteFileCommand implements Command {

    private FileSystemReceiver fileSystem;


    public WriteFileCommand(FileSystemReceiver fileSystem){
        this.fileSystem=fileSystem;
    }

    @Override
    public void execute() {
        this.fileSystem.writeFile();
    }
}
