package com.wanghang.code.io.IO.network;


/**
 *1:Socket:Socket可以认为是两个互联机器终端,对于一个网络连接，两端都有一个Socket，通过套接字进行交互通信。
 *       用Socket和Channel的网络编程，核心即为Socket和Channel，可以从Socket中获取InputStream和OutputStream，将其作为输入输出流，使应用程序与操作本地文件IO类似。
 *
 *2:ServerSocket和Socket:
 *   ServerSocket用于服务器端，监听客户端连接,可以获取Socket连接，
 *   Socket用于客户端与服务端交互
 *   服务段accept()方法处于阻塞状态，直到有客户端连接，创建一个服务端Socket，与客户端交互.
 *
 *
 *
 *3)Socket编程参考博文:
 * https://www.cnblogs.com/yiwangzhibujian/p/7107785.html#q2.1
 *
 *
 *
 *
 */
public class SocketDemo {
}
