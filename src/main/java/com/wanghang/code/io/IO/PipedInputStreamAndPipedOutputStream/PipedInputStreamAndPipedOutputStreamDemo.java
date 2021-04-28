package com.wanghang.code.io.IO.PipedInputStreamAndPipedOutputStream;


import java.io.*;

/**
 *1)Java IO：管道媒介
 *        1.1)进程通信和线程通信的区别:线程通信一般只需要使用共享内存的方式即可实现,
 *         而进程则可以通过管道进行通信(直接连接两个进程，一个进程写入管道，另一个进程可以读取管道，但是他不支持全双工，并且只能在父子进程间使用，所以局限性比较大).
 *       1.2)在Unix/Linux中管道可以作为两个位于不同空间进程通信的媒介，而在java中，管道只能为同一个JVM进程中的不同线程进行通信。
 *
 *2)操作管道相关的IO类为(字节):PipedInputStream和PipedOutputStream
 *  操作管道相关的IO类为(字符):或者是PipedReader和PipedWriter
 *
 *  一个PipedInputStream实例对象必须和一个PipedOutputStream实例对象进行连接而产生一个通信管道,
 *  PipedOutputStream向管道中写入数据，PipedIntputStream读取PipedOutputStream向管道中写入的数据,
 *  一个线程的PipedInputStream对象能够从另外一个线程的PipedOutputStream对象中读取数据。
 *
 *
 *4)关于管道（Pipe）通信的特点:
 *   4.1)管道为空时，读操作会被阻塞；管道满时，写操作会被阻塞,
 *   4.2)可以有多个进程读写，只是不能同时写,
 *   4.3)匿名管道只能单向，命名管道可以双向,
 *   4.4)管道是在内存中.
 *
 *todo：
 *5)这个管道媒介可以支持多大的数据？
 */
public class PipedInputStreamAndPipedOutputStreamDemo {

    public static void main(String[] args) throws IOException {
        //1:PipedInputStream和PipedOutputStream操作管道:
     //   pipedInputStream_PipedOutputStream();

        //2:PipedReader和PipedWriter操作管道:
        pipedReader_PipedWriter();
    }

    public static void pipedInputStream_PipedOutputStream() throws IOException {
        final PipedOutputStream output = new PipedOutputStream();
        final PipedInputStream input  = new PipedInputStream(output);

        //1:outputThread线程网管道里写入
        new Thread(()->{
            try {
                output.write("Hello world, pipe".getBytes());
            } catch (IOException e) {
                System.out.println("PipedOutputStream写入pipe异常");
            }finally {
                try {
                    output.close();
                } catch (IOException e) {
                    System.out.println("关闭PipedOutputStream异常");
                }
            }
        },"outputThread").start();

        //2:inputThread线程往管道里读取:
        new Thread(()->{
            try {
                int data;
                while((data=input.read()) != -1){
                    System. out.print(( char) data);
                }
            } catch (IOException e) {
                System.out.println("PipedInputStream 读取pipe数据异常");
            }finally {
                try {
                    input.close();
                } catch (IOException e) {
                    System.out.println("关闭PipedInputStream异常");
                }
            }
        },"inputThread").start();
    }

    public static void pipedReader_PipedWriter() throws IOException {
        final PipedWriter pipedWriter = new PipedWriter();
        final PipedReader pipedReader  = new PipedReader(pipedWriter);

        //1:PipedWriter线程网管道里写入
        new Thread(()->{
            try {
                pipedWriter.write("Hello world, pipe");
            } catch (IOException e) {
                System.out.println("PipedWriter写入pipe异常");
            }finally {
                try {
                    pipedWriter.close();
                } catch (IOException e) {
                    System.out.println("关闭PipedWriter异常");
                }
            }
        },"PipedWriterThread").start();

       //2:pipedReader往管道里面读取
        new Thread(()->{
            try {
                int data;
                while((data=pipedReader.read()) != -1){
                    System. out.print(( char) data);
                }
            } catch (IOException e) {
                System.out.println("PipedReader读取pipe数据异常");
            }finally {
                try {
                    pipedReader.close();
                } catch (IOException e) {
                    System.out.println("关闭PipedReader异常");
                }
            }
        },"PipedReaderThread").start();
    }
}
