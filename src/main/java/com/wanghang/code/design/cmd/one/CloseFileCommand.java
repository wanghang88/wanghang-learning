package com.wanghang.code.design.cmd.one;


//关闭文件的命令
public class CloseFileCommand implements Command{

    private FileSystemReceiver fileSystem;

    public CloseFileCommand(FileSystemReceiver fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public void execute() {
        this.fileSystem.closeFile();
    }
}
