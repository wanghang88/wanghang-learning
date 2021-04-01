package com.wanghang.code.design.cmd.two.cmd;


import com.wanghang.code.design.cmd.two.cmd.Command;
import com.wanghang.code.design.cmd.two.receiver.Receiver;

/**
 *
 * 撤销命令的具体实现
 */
public class UndoCommand implements Command {

    /**
     * 命令接受者对象
     */
    public Receiver receiver;

    /**
     * 构造方法
     * @param receiver
     */
    public UndoCommand(Receiver receiver){
        this.receiver = receiver;
    }

    /**
     * 具体的实现细节
     */
    public void execute() {
        this.receiver.undo();
    }
}
