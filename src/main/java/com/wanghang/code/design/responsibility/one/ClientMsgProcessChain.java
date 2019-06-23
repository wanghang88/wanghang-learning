package com.wanghang.code.design.responsibility.one;


import java.util.ArrayList;
import java.util.List;

/**
 * 客户端处理的人口以及添加责任链的人口
 */
public class ClientMsgProcessChain {

    private List<Process> chains = new ArrayList<>() ;

    /**
     * 添加责任链
     * @param process
     * @return
     */
    public ClientMsgProcessChain addChain(Process process){
        chains.add(process) ;
        return this ;
    }


    /**
     * 执行处理
     * @param msg
     */
    public void process(String msg){
        for (Process chain : chains) {
            chain.doProcess(msg);
        }
    }
}
