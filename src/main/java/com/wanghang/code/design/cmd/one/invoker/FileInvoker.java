package com.wanghang.code.design.cmd.one.invoker;


import com.wanghang.code.design.cmd.one.command.Command;

//命令的具体执行者
public class FileInvoker {

    private Command command;

    public FileInvoker(Command c) {
        this.command = c;
    }

    public void execute() {
        this.command.execute();
    }
}
