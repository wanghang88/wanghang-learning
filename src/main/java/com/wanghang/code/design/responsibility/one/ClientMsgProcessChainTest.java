package com.wanghang.code.design.responsibility.one;


/**
 * 第一种责任链方式测试
 */
public class ClientMsgProcessChainTest {

    public static void main(String[] args) {
        String msg = "内容内容内容==" ;
        ClientMsgProcessChain chain = new ClientMsgProcessChain()
                .addChain(new SensitiveWordProcess())
                .addChain(new TypoProcess())
                .addChain(new CopyrightProcess()) ;


        //执行请求（责任链上的执行器都会执行）
        chain.process(msg) ;
    }
}
