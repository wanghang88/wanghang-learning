package com.wanghang.code.io.NIO.network.v1;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 *NIO的客户端：
 */
public class NIOClient {
    public static void main(String[] args) {
       new Thread(new ClientHandle("127.0.0.1", 8080), "Client-001").start();
    }




    //客户端处理线程
  static class ClientHandle implements Runnable {
        private String host;
        private int port;

        private Selector selector;
        private SocketChannel socketChannel;

        private volatile boolean stop;

        public ClientHandle(String host, int port) {
            this.host = host == null ? "127.0.0.1" : host;
            this.port = port;
            try {
                //1:打开SocketChannel,用于创建客户端连接
                socketChannel = SocketChannel.open();
                //2:设置SocketChannel为非阻塞模式
                socketChannel.configureBlocking(false);
                //3:创建多路复用器（在Reactor线程中）
                selector = Selector.open();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }


        @Override
        public void run() {
            try {
                //4:socketChannel发起连接:
                if (socketChannel.connect(new InetSocketAddress(host, port))) {

                    //5:连接成功的话,则注册selector读事件:
                    socketChannel.register(selector, SelectionKey.OP_READ);

                    //6:向服务端发送消息或者读取应答
                    String message = "QUERY TIME ORDER";
                    ByteBuffer buffer = ByteBuffer.allocate(message.getBytes().length);      //ByteBuffer 分配内存
                    buffer.put(message.getBytes());
                    buffer.flip();
                    socketChannel.write(buffer);

                    if (!buffer.hasRemaining()) {
                        System.out.println("Send order 2 server succeed.");
                    }
                } else {
                    socketChannel.register(selector, SelectionKey.OP_CONNECT);   //客户端连接服务端事件
                }

                while (!stop) {
                    try {
                        //7：多路复用器在run的无限循环体内轮询准备就绪的Key
                        selector.select(1000);
                        Set<SelectionKey> selectedKeys = selector.selectedKeys();
                        Iterator<SelectionKey> it = selectedKeys.iterator();

                        SelectionKey key = null;
                        while (it.hasNext()) {
                            key = it.next();
                            it.remove();

                            if (key.isValid()) {                             //SelectionKey的isValid()方法
                                //8:将连接成功的Channel注册到Selector:
                                SocketChannel sc = (SocketChannel) key.channel();
                                if (key.isConnectable()) {                 // 判断是否连接成功SelectionKey.isConnectable()方法
                                    if (sc.finishConnect()) {               //SocketChannel的finishConnect()方法
                                        //9：SocketChannel注册读事件
                                        sc.register(selector, SelectionKey.OP_READ);

                                        //请求消息或者读取应答:
                                        String message = "QUERY TIME ORDER";
                                        ByteBuffer writeBuffer = ByteBuffer.allocate(message.getBytes().length);
                                        writeBuffer.put(message.getBytes());
                                        writeBuffer.flip();
                                        sc.write(writeBuffer);

                                        if (!writeBuffer.hasRemaining()) {
                                            System.out.println("Send order 2 server succeed.");
                                        }
                                    } else {
                                        System.exit(1);       // 连接失败,正常退出
                                    }
                                }
                            }
                            //监听读操作，读取服务端写回的网络信息
                            if (key.isReadable()) {                    //SelectionKey的isReadable()方法
                                //10：读取信息到缓冲区
                                SocketChannel sc = (SocketChannel) key.channel();
                                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                                int readBytes = sc.read(readBuffer);

                                if (readBytes > 0) {
                                    readBuffer.flip();

                                    byte[] bytes = new byte[readBuffer.remaining()];
                                    readBuffer.get(bytes);
                                    String body = new String(bytes, "UTF-8");
                                    System.out.println("Now is : " + body);
                                    this.stop = true;
                                } else if (readBytes < 0) {                  //等于0不做处理
                                    key.cancel();
                                    sc.close();
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.exit(1);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);       // 正常退出
            }


            if(selector!=null){
                try {
                    selector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
