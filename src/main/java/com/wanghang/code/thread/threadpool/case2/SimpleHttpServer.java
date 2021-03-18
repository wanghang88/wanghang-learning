package com.wanghang.code.thread.threadpool.case2;


import com.wanghang.code.thread.threadpool.cas1.DefaultThreadPool;
import com.wanghang.code.thread.threadpool.cas1.ThreadPool;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *通过线程池来构建简单的Web服务器：
 *                          这个web服务器来处理简单的Http请求,目前只能处理简单的文本和JPG图片的内容;
 *核心处理流程：
 *          使用main线程不断第接受客户端的Socket连接,将连接已请求的方式交给线程池处理
 */

public class SimpleHttpServer {

    private static ThreadPool<HttpRequestHandler> threadPool=new DefaultThreadPool();


    //SimpleHttpServer服务器的跟路径
    private static String basePath;

    private static ServerSocket serverSocket;

    private static int port=8080;


    public static void setPort(int port){
        if (port>0){
            SimpleHttpServer.port=port;
        }
    }


    public static void setBasePath(String basePath){
        if(basePath!=null && new File(basePath).exists() && new File(basePath).isDirectory()){
            SimpleHttpServer.basePath=basePath;
        }
    }


    //启动SimpleHttpServer服务器：
    public  static void start() throws IOException {
        serverSocket=new ServerSocket(port);
        Socket socket=null;
        while ((socket=serverSocket.accept())!=null){


        }
        serverSocket.close();
    }




   static class HttpRequestHandler implements Runnable{
        private Socket socket;
        public HttpRequestHandler(Socket socket){
            this.socket=socket;
        }

        @Override
        public void run() {


        }
    }
}
