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
 *                   Selector 是异步IO的核心,Selector就是注册各种I/O事件地方,它可以注册到很多个Channel上，监听各个Channel上发生的事件,并且能够根据事件情况决定Channel读写, 这样，通过一个线程管理多个Channel，就可以处理大量网络连接了.
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
 *4)NIO(非阻塞式I/O)的原理及通信模型:
 *      4.1)由一个专门的线程来处理所有的 IO 事件，并负责分发,
 *      4.2)事件驱动机制：事件到的时候触发，而不是同步的去监视事件,
 *      4.3)线程通讯：线程之间通过 wait,notify 等方式通讯。保证每次上下文切换都是有意义的。减少无谓的线程切换.
 *
 *      NIO采用了双向通道（channel）进行数据传输每个线程的处理流程大概都是读取数据、解码、计算处理、编码、发送响应,
 *      服务端和客户端各自维护一个管理通道的对象，我们称之为selector,该对象能检测一个或多个通道(channel) 上的事件.
 *
 *5)IO与NIO处理的对比:
 *      5.1)某时刻客户端给服务端发送了一些数据，阻塞I/O这时会调用read()方法阻塞地读取数据,
 *                                       NIO则服务端的selector上注册了读事件，服务端的处理线程会轮询地访问selector，如果访问selector时发现有感兴趣的事件到达，则处理这些事件，
 *                                       如果没有感兴趣的事件到达，则处理线程会一直阻塞直到感兴趣的事件到达为止
 *
 *
 *
 *
 *6)NIO核心Selector的基本使用流程:
 * 6.1)通过 Selector.open() 打开一个 Selector,
 * 6.2)将 Channel 注册到 Selector 中, 并设置需要监听的事件(interest set)
 * 6.3)不断重复：
 *            调用 select() 方法,
 *            调用 selector.selectedKeys() 获取 selectedkeys,迭代每个 selectedkey
 *
 *            处理单个selectedkey:从Selectedkey中获取 对应的 Channel 和附加信息(如果有的话),
 *            判断是哪些 IO 事件已经就绪了, 然后处理它们,
 *            根据需要更改 selected key 的监听事件
 *            将已经处理过的 key 从 selected keys 集合中删除.
 */
public class NioNetworkDemo {

}
