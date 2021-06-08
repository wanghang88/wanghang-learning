package com.wanghang.code.io.NIO.network.v2;


import com.wanghang.code.io.NIO.network.v2.utils.Calculator;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


/**
 *Java NIO客户端第二个版本:
 *参考实现博文：
 *          https://www.jianshu.com/p/ac0bff2d8e46
 *
 *          https://blog.csdn.net/anxpp/article/details/51512200   (这里面有AIO,到时候也可以参考下代码)
 *
 *
 *
 */
public class NIOServer {
    private static int DEFAULT_PORT = 12345;
    private static ServerHandle serverHandle;

    public static void main(String[] args) {
        start();
    }





    public static void start(){
        start(DEFAULT_PORT);
    }
    public static synchronized void start(int port){
        //1:刚开始启动的时候,有服务端的线程在运行的话则停掉,然后再重新开一个
        if(serverHandle!=null){
            serverHandle.stop();
        }
        serverHandle = new ServerHandle(port);
        new Thread(serverHandle,"NIOServer").start();
    }

    static class ServerHandle implements Runnable{
        private Selector selector;
        private ServerSocketChannel serverChannel;
        private volatile boolean started;


        public ServerHandle(int port){
            try {
                //打开监听通道
                serverChannel = ServerSocketChannel.open();
                //如果为 true，则此通道将被置于阻塞模式；如果为 false，则此通道将被置于非阻塞模式
                //与Selector一起使用时，Channel必须处于非阻塞模式下。这意味着不能将FileChannel与Selector一起使用，因为FileChannel不能切换到非阻塞模式。而套接字通道都可以。
                serverChannel.configureBlocking(false);//开启非阻塞模式
                //绑定端口 backlog设为1024
                serverChannel.socket().bind(new InetSocketAddress("127.0.0.1",port),1024);

                //创建Selector
                selector = Selector.open();

                //监听客户端连接请求
                //只要ServerSocketChannel及SocketChannel向Selector注册了特定的事件，Selector就会监控这些事件是否发生。
                serverChannel.register(selector, SelectionKey.OP_ACCEPT);
                //标记服务器已开启
                started = true;
                System.out.println("服务器已启动，端口号：" + port);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }


        //暂停服务端
        public void stop(){
            started = false;
        }


        @Override
        public void run() {
            //轮询selector
            while (started){
                try {
                    //无论是否有读写事件发生，selector每隔1s被唤醒一次
                    selector.select();                                        //selector.select()与 selector.select(1000)的区别;
                    //阻塞,只有当至少一个注册的事件发生的时候才会继续.
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> it = keys.iterator();
                    SelectionKey key = null;
                    while(it.hasNext()){
                        key = it.next();
                        //每次迭代末尾的remove()调用，Selector不会自己从已选择集中移除SelectioKey实例，必须在处理完通道时自己移除。
                        it.remove();
                        try{
                            handleInput(key);
                        }catch(Exception e){
                            if(key != null){
                                //会在再次获取已触犯事件的api时，进行cannel
                                key.cancel();
                                if(key.channel() != null){
                                    key.channel().close();
                                }
                            }
                        }
                    }
                    //selector关闭后会自动释放里面管理的资源
                    if(selector != null) {
                        try {
                            selector.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //服务端处理事件
        private void handleInput(SelectionKey key) throws IOException {
            if(key.isValid()){
                //处理新接入的请求消息
                if(key.isAcceptable()){
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    //通过ServerSocketChannel的accept创建SocketChannel实例
                    //完成该操作意味着完成TCP三次握手，TCP物理链路正式建立
                    SocketChannel sc = ssc.accept();
                    //设置为非阻塞的
                    sc.configureBlocking(false);
                    //注册为读
                    sc.register(selector, SelectionKey.OP_READ);
                }

                //读取消息：
                if(key.isReadable()){
                    SocketChannel sc = (SocketChannel) key.channel();
                    //创建ByteBuffer，并开辟一个1M的缓冲区
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    //读取请求码流，返回读取到的字节数
                    int readBytes = sc.read(buffer);
                    //读取到字节，对字节进行编解码
                    if(readBytes>0){
                        //将缓冲区当前的limit设置为position=0，用于后续对缓冲区的读取操作
                        buffer.flip();
                        //根据缓冲区可读字节数创建字节数组
                        byte[] bytes = new byte[buffer.remaining()];
                        //将缓冲区可读字节数组复制到新建的数组中
                        buffer.get(bytes);
                        String expression = new String(bytes,"UTF-8");
                        System.out.println("服务器收到消息：" + expression);
                        //处理数据
                        String result = null;
                        try{
                           result = Calculator.cal(expression).toString();
                        }catch(Exception e){
                            result = "计算错误：" + e.getMessage();
                        }
                        //发送应答消息
                        doWrite(sc,result);
                    }else if(readBytes<0){
                        key.cancel();
                        sc.close();
                    }
                }
            }
        }


        //异步发送应答消息:
        private void doWrite(SocketChannel channel, String response) throws IOException {
            //将消息编码为字节数组
            byte[] bytes = response.getBytes();
            //根据数组容量创建ByteBuffer
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            //将字节数组复制到缓冲区
            writeBuffer.put(bytes);
            //flip操作
            writeBuffer.flip();
            //发送缓冲区的字节数组
            channel.write(writeBuffer);                     //****此处不含处理“写半包”的代
        }
    }
}
