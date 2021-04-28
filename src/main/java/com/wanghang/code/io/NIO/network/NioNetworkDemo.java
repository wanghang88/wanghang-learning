package com.wanghang.code.io.NIO.network;


/**
 *1)网络编程NIO(异步):
 *               是一种没有阻塞地读写数据的方法，通常，在代码进行 read() 调用时，代码会阻塞直至有可供读取的数据。同样， write()调用将会阻塞直至数据能够写入。
 *               并且对于特定的事件 I/O (诸如数据可读、新建连接等),系统会告知。
 *
 *
 *2)异步 I/O 的一个优势在于:
 *                     允许同时大量的输入和输出执行 I/O,通过监听通道上的事件
 *
 *
 *3)异步IO的三大核心对象,Buffer和Channel,Selector
 *                   Selector 是异步IO的核心。
 *
 *
 *
 *4)Selector:它可以注册到很多个Channel上，监听各个Channel上发生的事件,并且能够根据事件情况决定Channel读写
 *           这样，通过一个线程管理多个Channel，就可以处理大量网络连接了.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class NioNetworkDemo {







}
