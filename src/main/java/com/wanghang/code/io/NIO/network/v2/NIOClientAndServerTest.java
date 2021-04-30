package com.wanghang.code.io.NIO.network.v2;


import java.util.Scanner;

public class NIOClientAndServerTest {

    public static void main(String[] args) throws Exception {
        //运行服务器
        NIOServer.start();
        //避免客户端先于服务器启动前执行代码
        Thread.sleep(100);

        //运行客户端
        NIOClient.start();

        //发送消息知道字符为"q",就会停止
        while(NIOClient.sendMsg(new Scanner(System.in).nextLine()));
    }
}
