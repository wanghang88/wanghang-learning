package com.wanghang.code.design.cmd;


/**
 * 具体的执行命令的角色
 *添加的命令
 */
public class AddCommand implements Command {

    /**
     * 命令接受者对象
     */
    public Receiver receiver;

    /**
     * 构造方法
     * @param receiver
     */
    public AddCommand(Receiver receiver){
        this.receiver = receiver;
    }



    /**
     * 具体执行新增的实现
     */
    public void execute() {
        this.receiver.add();
    }
}
