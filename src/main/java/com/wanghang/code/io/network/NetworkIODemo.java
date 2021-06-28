package com.wanghang.code.io.network;

/**
 *关于IO网络通信
 * BIO:
 *     全称Block-IO 是一种同步且阻塞的通信模式,是一个比较传统的通信方式，模式简单，使用方便。但并发处理能力低，通信耗时，依赖网速,
 * NIO:
 *     全称 Non-Block IO,是Java SE 1.4版以后，针对网络传输效能优化的新功能。是一种非阻塞同步的通信模式,
 *     原来的 I/O 以流的方式处理数据，而 NIO 以块的方式处理数据
 *     面向流的 I/O 系统一次一个字节地处理数据。一个输入流产生一个字节的数据，一个输出流消费一个字节的数据,
 *     面向块的 I/O 系统以块的形式处理数据。每一个操作都在一块中产生或者消费一个数据块。按块处理数据比按(流式的)字节处理数据要快得多。
 *AIO,
 *     全称 Asynchronous IO,是异步非阻塞的IO,在NIO的基础上引入了新的异步通道的概念,并提供了异步文件通道和异步套接字通道的实现
 *
 *
 *2:工具：NetAssist 网络调试助手;
 *
 *
 *3:参考博文:(小傅哥)
 https://bugstack.cn/itstack-demo-netty-1/2019/07/30/netty%E6%A1%88%E4%BE%8B-netty4.1%E5%9F%BA%E7%A1%80%E5%85%A5%E9%97%A8%E7%AF%87%E9%9B%B6-%E5%88%9D%E5%85%A5JavaIO%E4%B9%8B%E9%97%A8BIO-NIO-AIO%E5%AE%9E%E6%88%98%E7%BB%83%E4%B9%A0.html
 *
 */
public class NetworkIODemo{

}
