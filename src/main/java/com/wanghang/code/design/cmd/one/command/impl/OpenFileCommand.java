package com.wanghang.code.design.cmd.one.command.impl;


import com.wanghang.code.design.cmd.one.command.Command;
import com.wanghang.code.design.cmd.one.receiver.FileSystemReceiver;

//打开文件的命令
public class OpenFileCommand implements Command {

    private FileSystemReceiver fileSystem;

    public OpenFileCommand(FileSystemReceiver fileSystem){
        this.fileSystem=fileSystem;
    }


    @Override
    public void execute() {
        // open command is forwarding request to openFile method
        this.fileSystem.openFile();
    }


}
