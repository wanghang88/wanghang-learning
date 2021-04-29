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
 *                   Selector 是异步IO的核心,Selector就是注册各种I/O事件地方,
 *                            当有读或写等任何注册的事件发生时，可以从Selector中获得相应的SelectionKey,同时从 SelectionKey中可以找到发生的事件和该事件所发生的具体的SelectableChannel，以获得客户端发送过来的数据.
 *                            要使用Selector，得向Selector注册Channel，然后调用它的select()方法,这个方法会一直阻塞到某个注册的通道有事件就绪,一旦这个方法返回，线程就可以处理这些事件(比如:新建连接,数据接收)
 *                            通道上可以注册我们感兴趣的事件,一共有四种事件：
 *                                                                 服务端接收客户端连接事件：SelectionKey.OP_ACCEPT(16)
 *                                                                 客户端连接服务端事件：SelectionKey.OP_CONNECT(8)
 *                                                                 读事件：SelectionKey.OP_READ(1)
 *                                                                 写事件:SelectionKey.OP_WRITE(4)
 *
 *
 *
 *                  关键的Channel实现: FileChannel,DatagramChannel,SocketChannel,ServerSocketChannel,这些通道涵盖了UDP 和 TCP 网络IO，以及文件IO.
 *                  关键Buffer的实现：ByteBuffer,CharBuffer,DoubleBuffer,FloatBuffer,IntBuffer,LongBuffer,ShortBuffer这些Buffer,覆盖了你能通过IO发送的基本数据类型：byte, short, int, long, float, double 和 char
 *
 *
 *
 *
 *
 *4)Selector:它可以注册到很多个Channel上，监听各个Channel上发生的事件,并且能够根据事件情况决定Channel读写
 *           这样，通过一个线程管理多个Channel，就可以处理大量网络连接了.
 *
 *
 *
 */
public class NioNetworkDemo {


}
