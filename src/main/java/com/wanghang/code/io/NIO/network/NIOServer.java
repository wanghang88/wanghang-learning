package com.wanghang.code.io.NIO.network;


import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 *
 *NIO的服务端:
 *
 *
 * NIO网络模型参考博文：
 *                 https://www.cnblogs.com/duanxz/p/6759689.html
 */


public class NIOServer {

    public static void main(String[] args) {
        new Thread(new ServerReactorTask()).start();
    }



    static class ServerReactorTask implements Runnable{
        private Selector selector;

       public ServerReactorTask(){

           try {
               //1:打开ServerSocketChannel，用于监听客户端的连接，它是所有客户端连接的父管道
               ServerSocketChannel acceptorSvr = ServerSocketChannel.open();

               //2:监听8080端口,设置连接为非阻塞模式
               acceptorSvr.socket().bind(new InetSocketAddress("127.0.0.1", 8080));
               acceptorSvr.configureBlocking(false);

               //3:创建Reactor线程，创建多路复用器并启动线程
               selector = Selector.open();

               //4:将ServerSocketChannel注册到Reactor线程的多路复用器Selector上，监听Accept事件
               SelectionKey key = acceptorSvr.register(selector, SelectionKey.OP_ACCEPT);


           } catch (IOException e) {
               e.printStackTrace();
           }
       }

        @Override
        public void run() {
            //5:在run方法中无限循环体内轮询准备就绪的Key
            while (true) {
                try {
                    selector.select(1000);
                    Set<SelectionKey> selectedKeys = selector.selectedKeys();
                    Iterator<SelectionKey> it = selectedKeys.iterator();
                    SelectionKey key = null;
                    while (it.hasNext()) {
                        key = it.next();
                        it.remove();

                        if (key.isValid()) {
                            if(key.isAcceptable()){   // 处理新接入的请求消息
                                //6:Selector监听到有新的客户端接入，处理新的接入请求，完成TCP三次握手，建立物理链路
                                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                                SocketChannel sc = ssc.accept();

                                //7:设置客户端链路为非阻塞模式
                                sc.configureBlocking(false);
                                sc.socket().setReuseAddress(true);

                                //8:将新接入的客户端连接注册到Reactor线程的Selector上，监听读操作，读取客户端发送的网络消息
                                sc.register(selector, SelectionKey.OP_READ);
                            }

                            if(key.isReadable()){       //读事件准备就绪，处理客户客户端的读事件

                                //9:异步读取客户端请求消息到缓存区
                                SocketChannel sc = (SocketChannel) key.channel();
                                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                                int readBytes = sc.read(readBuffer);

                                //10:对ByteBuffer进行编解码，如果有半包消息指针reset，继续读取后续的报文
                                if(readBytes>0){
                                    readBuffer.flip();
                                    byte[] bytes = new byte[readBuffer.remaining()];                   //readBuffer.remaining()方法
                                    readBuffer.get(bytes);
                                    String body = new String(bytes, "UTF-8");
                                    System.out.println("The time server receive order : " + body);

                                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(System.currentTimeMillis()).toString() : "BAD ORDER";


                                    //写应答
                                    byte[] bytes2 = currentTime.getBytes();
                                    ByteBuffer writeBuffer = ByteBuffer.allocate(bytes2.length);
                                    writeBuffer.put(bytes2);
                                    writeBuffer.flip();
                                    sc.write(writeBuffer);
                                }else if(readBytes<0){
                                    // 对端链路关闭
                                    key.cancel();
                                    sc.close();
                                }
                            }
                        }
                    }
                } catch (IOException e) {

                }
            }
        }
    }

}
