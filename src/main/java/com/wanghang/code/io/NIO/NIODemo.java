package com.wanghang.code.io.NIO;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *1)NIO:即New IO，这个库是在JDK1.4中才引入的。NIO和IO有相同的作用和目的，但实现方式不同,NIO主要用到的是块，所以NIO的效率要比IO高很多.
 *      在java的API中提供了两套API:标准的输入输出NIO和网络编程NIO
 *      NIO是以块的方式处理数据
 *
 *2)IO和NIO的区别：
 *      NIO和IO最大的区别是数据打包和传输方式。
 *      IO是以流的方式处理数据(面向流的IO)：一次一个字节的处理数据，一个输入流产生一个字节，一个输出流就消费一个字节，比较简单但是比较慢。
 *      NIO是以块的方式处理数据(面向块的IO):以块的形式处理数据,每一个操作都在一步中产生或消费一个数据块,比按流处理要快,但是要比IO要复杂。
 *
 *
 *3)NIO的基石:
 *         3.1)Buffer和Channel是标准NIO中的核心对象(网络NIO中还有个Selector核心对象),几乎每一个IO操作中都会用到它们。
 *         Channel：Channel是对原IO中流的模拟，任何来源和目的数据都必须通过一个Channel对象。
 *                  Channel不能直接操作数据，只能通过Buffer操作数据。
 *                  可以通过它读取和写入数据。可以把它看做IO中的流。但是它和流相比还有一些不同
 *                  不同点：
 *                       Channel是双向的，既可以读又可以写，而流是单向的,
 *                       Channel可以进行异步的读写,
 *                       对Channel的读写必须通过buffer对象

 *         Buffer:实质上是一个容器对象，发给Channel的所有对象都必须先放到Buffer中；同样的，从Channel中读取的任何数据都要读到Buffer中，
 *                在NIO中，数据是放入buffer对象的，而在IO中，数据是直接写入或者读到Stream对象的。
 *                应用程序不能直接对 Channel 进行读写操作，而必须通过 Buffer 来进行，即 Channel 是通过 Buffer 来读写数据的.
                  Buffer读写数据一般遵循以下四个步(当向Buffer写入数据时，Buffer会记录下写了多少数据。一旦要读取数据，需要通过flip() 方法将Buffer从写模式切换到读模式,在读模式下，可以读取之前写入到 Buffer 的所有数据，一旦读完了所有的数据，就需要清空缓冲区，让它可以再次被写入):
                     写入数据到 Buffer(写),
                     调用 flip() 方法(切换读),
                     从 Buffer 中读取数据(读),
                     调用 clear() 方法或者 compact() 方法(清空缓存)
 *
 *
 *4)Java NIO中Channel主要有如下几种类型:
 *              FileChannel：从文件读取数据的
 *              DatagramChannel：读写UDP网络协议数据
 *              SocketChannel：读写TCP网络协议数据
 *              ServerSocketChannel：可以监听TCP连接
 *
 *5)NIO读写的基本流程:
 *              IO中的读和写，对应的是数据和Stream,NIO中的读和写，则对应的就是通道和缓冲区.
 *              NIO中从通道中读取:创建一个缓冲区,然后让通道读取数据到缓冲区,
 *              NIO写入数据到通道:创建一个缓冲区,然后让通道用这些数据来执行写入.
 *
 *
 *6)NIO读写文件的步骤:
 *                读(文件)：
 *                从FileInputStream获取Channel(),也就是获取通道
 *                创建Buffer
 *                从Channel读取数据到Buffer
 *                写(文件)：
 *                      从FileInputStream获取Channel(),也就是获取通道
 *                      创建缓冲区，将数据放入缓冲区
 *                      把缓冲区数据写入通道中
 *
 *
 *
 *7)NIO操作文件参考博文：
 *                 https://blog.csdn.net/suifeng3051/article/details/48160753
 */
public class NIODemo {


    public static void main(String[] args) throws IOException {
        //准备数据文件：
        dataReady();


        //1:使用NIO复制文件:
        copyFileUseNIO();


        //2:使用普通字符流复制文件:
        Reader_Writer_CopyFile();



        //3:普通字符流使用数组复制文件：
        Reader_Writer_Array_CopyFile();



        //4:缓冲区字符流复制文件:
        BufferedReader_BufferedWriter_CopyFile();
    }




    public static void copyFileUseNIO() throws IOException {
        File sourFile = new File("D:/wanghang/data.txt");
        //1:准备文件(数据)
        if(!(sourFile.exists() && sourFile.length()>0)){
            dataReady();
        }

        //2:声明数据源和目标
        File targetFile = new File("D:/wanghang/FileChannel.txt");
        long start = System.currentTimeMillis();
        FileInputStream fi=new FileInputStream(sourFile);             //数据源
        FileOutputStream fo=new FileOutputStream(targetFile);         //需要复制到的目标文件

        //3:获得传输通道channel
        FileChannel inChannel=fi.getChannel();
        FileChannel outChannel=fo.getChannel();
        //4:创建ByteBuffer容器
        ByteBuffer buffer=ByteBuffer.allocate(1024);

        while(true){
            //判断是否读完文件
            int eof =inChannel.read(buffer);
            if(eof==-1){
                break;
            }
            //重设一下buffer的position=0，limit=position
            buffer.flip();
            //开始写
            outChannel.write(buffer);
            //写完要重置buffer，重设position=0,limit=capacity
            buffer.clear();
        }
        inChannel.close();
        outChannel.close();
        fi.close();
        fo.close();
        long end = System.currentTimeMillis();
        System.out.println("使用NIO耗费:：" + (end - start) + " ms,targetFile文件大小：" + targetFile.length() / 1024 + " kb");
    }


    public static void Reader_Writer_CopyFile() throws IOException {
        File sourFile = new File("D:/wanghang/data.txt");
        //1:准备文件(数据):
        if(!(sourFile.exists() && sourFile.length()>0)){
            dataReady();
        }

        //2:普通字符流使用数组读写
        long start2 = System.currentTimeMillis();
        File targetFile = new File("D:/wanghang/Writer.txt");
        Reader reader = new FileReader(sourFile);
        Writer writer = new FileWriter(targetFile);

        int by;
        while ((by=reader.read()) != -1) {
            writer.write(by);
        }
        reader.close();
        writer.close();
        long end2 = System.currentTimeMillis();
        System.out.println("普通字符流耗时：" + (end2 - start2) + " ms,文件大小：" + targetFile.length() / 1024 + " kb");
    }

    public static void Reader_Writer_Array_CopyFile() throws IOException {
        File sourFile = new File("D:/wanghang/data.txt");
        //1:准备文件(数据):
        if(!(sourFile.exists() && sourFile.length()>0)){
            dataReady();
        }

        //2:普通字符流使用数组读写
        long start2 = System.currentTimeMillis();
        File targetFile = new File("D:/wanghang/Writer_array.txt");
        Reader reader = new FileReader(sourFile);
        Writer writer = new FileWriter(targetFile);

        char[] chs = new char[1024];

        while ((reader.read(chs)) != -1) {
            writer.write(chs);
        }
        reader.close();
        writer.close();
        long end2 = System.currentTimeMillis();
        System.out.println("普通字符流使用数组：" + (end2 - start2) + " ms,文件大小：" + targetFile.length() / 1024 + " kb");
    }

    public static void BufferedReader_BufferedWriter_CopyFile() throws IOException {
        File sourFile = new File("D:/wanghang/data.txt");
        //1:准备文件(数据):
        if(!(sourFile.exists() && sourFile.length()>0)){
            dataReady();
        }

        //2:使用缓冲字符流复制文件:
        long start = System.currentTimeMillis();
        File targetFile = new File("D:/wanghang/BufferedWriter.txt");
        BufferedReader br = new BufferedReader(new FileReader(sourFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile));

        String line = null;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
        long end = System.currentTimeMillis();
        System.out.println("缓冲字符流耗时：" + (end - start) + " ms,BufferedWriter文件大小：" + targetFile.length() / 1024 + " kb");
    }













    //准备数据：D:/wanghang/data.txt
    public static void dataReady() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("abcdefghijklmnopqrstuvwxyz");
        }
        File file = new File("D:/wanghang/data.txt");
        OutputStream os = new FileOutputStream(file);
        os.write(sb.toString().getBytes());
        os.close();
        System.out.println("完毕");
    }
}
