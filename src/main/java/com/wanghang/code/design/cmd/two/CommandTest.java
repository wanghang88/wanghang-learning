package com.wanghang.code.design.cmd.two;


import com.wanghang.code.design.cmd.two.cmd.AddCommand;
import com.wanghang.code.design.cmd.two.cmd.RedoCommand;
import com.wanghang.code.design.cmd.two.cmd.UndoCommand;
import com.wanghang.code.design.cmd.two.invoker.Invoker;
import com.wanghang.code.design.cmd.two.receiver.Receiver;

public class CommandTest {


    public static void main(String[] args) {
        Receiver receiver=new Receiver();
        AddCommand addCmd = new AddCommand(receiver); //具体命令实体对象
        UndoCommand undoCmd = new UndoCommand(receiver); //具体命令实体对象
        RedoCommand redoCmd = new RedoCommand(receiver); //具体命令实体对象


        Invoker invoker = new Invoker(); //调用者对象
        invoker.setCommand(addCmd);
        invoker.execute();
        invoker.setCommand(addCmd);
        invoker.execute();
        invoker.setCommand(undoCmd);
        invoker.execute();
        invoker.setCommand(redoCmd);
        invoker.execute();
    }
}
