package com.wanghang.code.design.cmd;



public class RedoCommand implements Command {

    /**
     * 命令接受者对象
     */
    public Receiver receiver;


    /**
     * 构造方法
     * @param receiver
     */
    public RedoCommand(Receiver receiver){
        this.receiver = receiver;
    }


    public void execute() {
        this.receiver.redo();
    }
}
