package com.wanghang.code.io.IO;

import java.io.*;

/**
 *1)IO的概念：
 *      即in和out，也就是输入和输出，指应用程序和外部设备之间的数据传递，常见的外部设备包括文件、管道、网络连接，
 *
 *      java中通过流处理IO:
 *                     流（Stream），是一个抽象的概念，是指一连串的数据（字符或字节），是以先进先出的方式发送信息的通道。
 *                     当程序需要读取数据的时候,就会开启一个通向数据源的流，这个数据源可以是文件，内存，或是网络连接，
 *                     当程序需要写入数据的时候，就会开启一个通向目的地的流。
 *      java中流Stream的特点:
 *                         先进先出：最先写入输出流的数据最先被输入流读取到,
 *                         顺序存取：可以一个接一个地往流中写入一串字节，读出时也将按写入顺序读取一串字节，不能随机访问中间的数据,
 *                         只读或只写:每个流只能是输入流或输出流的一种,输入流只能进行读操作，对输出流只能进行写操作
 *      java中IO流的分类:
 *                    按数据流的方向(输入流、输出流),是对于应用程序而言文件读写，读取文件是输入流，写文件是输出流
 *
 *                    按处理数据单位(字节流、字符流),字节流操作的单元是8位的字节,字符流操作的是单元为16位的字符
 *                                 产生字符流的原因：Java中字符是采用Unicode标准，Unicode 编码中，一个英文为一个字节，一个中文为两个字节，
 *                                                而在UTF-8编码中，一个中文字符是3个字节
 *
 *                                 字节流和字符流的区别:(字节流可以处理一切文件，而字符流只能处理纯文本文件)
 *                                                  字节流一般用来处理图像、视频、音频、PPT、Word等类型的文件,字符流一般用于处理纯文本类型的文件，如TXT文件。
 *                                                  字节流本身没有缓冲区，缓冲字节流相对于字节流，效率提升非常高,字符流本身就带有缓冲区，缓冲字符流相对于字符流效率提升就不是那么大了。
 *
 *
 *                    按功能：节点流、处理流:
 *                                  节点流:直接操作数据读写的流类，比如FileInputStream
 *                                  处理流:对节点流进行封装,例如BufferedInputStream(缓冲字节流),最终的数据处理还是由节点流完成.
 *                                  在诸多处理流中，有一个非常重要，那就是缓冲流,缓冲流:减少程序与磁盘的交互,缓冲流会先在内存中设置一个缓存区，缓冲区先存储足够的待操作数据后,再于磁盘进行交互,这样在数据总数不变的情况下,进行交互的次数会大大地减少从而提高性能。
 *
 *2):位、字节、字符:
 *              字节(Byte)是计量单位,表示数据量多少,用于计量存储容量的一种计量单位，通常情况下一字节等于八位，
 *              字符(Character):计算机中使用的字母、数字、字和符号，比如’A’、‘B’、’$’、’&'等，
 *              字符及中文在不同的编码中占用的字节数是不同的：
 *
 *              标点符号：英文标点为一个字节，中文标点为两个字节,例如：英文句号 . 占1个字节的大小，中文句号 。占2个字节的大小。
 *              UTF-8 编码中，一个英文字为一个字节，一个中文为三个字节,
 *              Unicode 编码中，一个英文为一个字节，一个中文为两个字节,
 *              ASCII 码中，一个英文字母（不分大小写）为一个字节，一个中文汉字为两个字节,
 *              UTF-16 编码中，一个英文字母字符或一个汉字字符存储都需要 2 个字节（Unicode 扩展区的一些汉字存储需要 4 个字节,
 *              UTF-32 编码中，世界上任何字符的存储都需要 4 个字节
 *
 *3)参考博文:
 *        https://blog.csdn.net/mu_wind/article/details/108674284
 *
 *
 *4)java io中常用的读写媒介,File媒介,管道媒介,网络媒介(其核心是Socket，同磁盘操作一样，java网络编程对应着两套API，即Java IO和Java)
 *
 */
public class IODemo {


    public static void main(String[] args) throws IOException {

        //1:字节流写入和缓冲字节流写入的效率对比:(BufferedOutputStream效率要低 Todo )
     //   OutputStream_BufferedOutputStream_Compare_write();


        //2:字节流读写和缓区字节流读写效率对比(效率:缓冲字节流效率明显要高于普通字节流)
        InputStreamAndOutputStream_BufferedInputStreamAndBufferedOutputStream_read_write();


        //3:字符流和缓冲字符流的效率对比(效率:普通字符流数组读写得效率>缓冲字符流>普通字符流得读写)
       // FileReaderAndFileWriter_BufferedReaderAndBufferedWriter_read_write();
        
    }



    private static void OutputStream_BufferedOutputStream_Compare_write() throws IOException {
        File file = new File("D:/wanghang/OutputStream_BufferedOutputStream_Compare_write.txt");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3000; i++) {
            sb.append("abcdefghigklmnopqrstuvwsyz");
        }
        byte[] bytes = sb.toString().getBytes();

        //OutputStream写入文件(普通字节流写入)：
        long start = System.currentTimeMillis();
        OutputStream os = new FileOutputStream(file);
        os.write(bytes);
        os.close();
        long end = System.currentTimeMillis();
        System.out.println("OutputStream字节流写入耗时：" + (end - start) + " ms");


        //BufferedOutputStream缓冲字节流写入
        long start2 = System.currentTimeMillis();
        BufferedOutputStream bo = new BufferedOutputStream(new FileOutputStream(file));
        bo.write(bytes);
        bo.close();
        long end2 = System.currentTimeMillis();
        System.out.println("BufferedOutputStream字节流写入耗时：" + (end2 - start2) + " ms");
    }


    private static void InputStreamAndOutputStream_BufferedInputStreamAndBufferedOutputStream_read_write() throws IOException {
        File data = new File("D:/wanghang/src.zip");
        File fileA = new File("D:/wanghang/a.zip");
        File fileB = new File("D:/wanghang/b.zip");

        StringBuilder sb = new StringBuilder();

        //1:普通字节流读写:
        long start = System.currentTimeMillis();
        //1.1:封装数据源
        InputStream is = new FileInputStream(data);
        //1.2: 封装目的地
        OutputStream os = new FileOutputStream(fileA);
        //1.3:文件的读写操作：
        int by = 0;
        while ((by = is.read()) != -1) {
            os.write(by);
        }
        is.close();
        os.close();
        long end = System.currentTimeMillis();
        System.out.println("普通字节流耗时：" + (end - start) + " ms");



        //2:缓冲字节流操作
        long start2 = System.currentTimeMillis();
        //2.1:封装数据源
        BufferedInputStream bi = new BufferedInputStream(new FileInputStream(data));
        //2.2:封装目的地
        BufferedOutputStream bo = new BufferedOutputStream(new FileOutputStream(fileB));

        int by2 = 0;
        while ((by2 = bi.read()) != -1) {
            bo.write(by2);
        }
        bo.close();
        bi.close();
        long end2 = System.currentTimeMillis();
        System.out.println("缓冲字节流耗时：" + (end2 - start2) + " ms");
    }


    private static void FileReaderAndFileWriter_BufferedReaderAndBufferedWriter_read_write() throws IOException {
        //1:准备数据(D:/wangahng/data.txt)：
        dataReady();
        File data = new File("D:/wanghang/data.txt");

        File fileA = new File("D:/wanghang/a.txt");
        File fileB = new File("D:/wanghang/b.txt");
        File fileC = new File("D:/wanghang/c.txt");

        //1:普通字符流读写，并且不实用数组
        long start = System.currentTimeMillis();
        Reader reader = new FileReader(data);
        Writer writer = new FileWriter(fileA);
        int ch = 0;
        while ((ch = reader.read()) != -1) {
            writer.write((char) ch);
        }
        reader.close();
        writer.close();
        long end = System.currentTimeMillis();
        System.out.println("普通字符流1耗时：" + (end - start) + " ms,文件大小：" + fileA.length() / 1024 + " kb");


        //2:也是普通字符流读取，不过使用数组
        long start2 = System.currentTimeMillis();
        Reader reader1 = new FileReader(data);
        Writer writer1 = new FileWriter(fileB);
        char[] chs = new char[1024];
        while ((reader1.read(chs)) != -1) {
            writer1.write(chs);
        }
        reader1.close();
        writer1.close();
        long end2 = System.currentTimeMillis();
        System.out.println("普通字符流2耗时：" + (end2 - start2) + " ms,文件大小：" + fileB.length() / 1024 + " kb");


        //3:缓冲字符流读取(readLine()和write(line))
        long start3 = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader(data));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileC));
        String line = null;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
            bw.flush();
        }
        // 释放资源
        bw.close();
        br.close();
        long end3 = System.currentTimeMillis();
        System.out.println("缓冲字符流耗时：" + (end3 - start3) + " ms,文件大小：" + fileC.length() / 1024 + " kb");
    }

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
