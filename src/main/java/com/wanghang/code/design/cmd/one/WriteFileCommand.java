package com.wanghang.code.design.cmd.one;


//写文件的命令
public class WriteFileCommand implements Command{

    private FileSystemReceiver fileSystem;


    public WriteFileCommand(FileSystemReceiver fileSystem){
        this.fileSystem=fileSystem;
    }

    @Override
    public void execute() {
        this.fileSystem.writeFile();
    }
}
