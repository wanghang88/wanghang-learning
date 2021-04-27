package com.wanghang.code.io.IO.BufferedInputStreamAndBufferedOutputStream;


import java.io.*;

/**
 * BufferedInputStream、BufferedOutputStream
 *缓冲字节流:
 *        缓冲字节流是为高效率而设计的，真正的读写操作还是靠FileOutputStream和FileInputStream，所以其构造方法入参是这两个类的对象.
 *
 *
 *
 *
 *
 */
public class BufferedInputStreamAndBufferedOutputStreamDemo {


    public static void main(String[] args) throws IOException {
        File file = new File("D:/test.txt");
        //1:通过BufferedOutputStream写入文件
        write(file);


        //2:通过BufferedInputStream读取text.txt文件
        String read = read(file);
        System.out.println("read:"+read);
    }


    public static void write(File file) throws IOException {
        // 缓冲字节流，提高了效率
        BufferedOutputStream bis = new BufferedOutputStream(new FileOutputStream(file, true));
        // 要写入的字符串
        String string = "松下问童子，言师采药去。只在此山中，云深不知处。";
        // 写入文件
        bis.write(string.getBytes());
        // 关闭流
        bis.close();
    }

    public static String read(File file) throws IOException {
        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
        // 一次性取多少个字节
        byte[] bytes = new byte[1024];
        // 用来接收读取的字节数组
        StringBuilder sb = new StringBuilder();
        // 读取到的字节数组长度，为-1时表示没有数据
        int length = 0;
        // 循环取数据
        while ((length = fis.read(bytes)) != -1) {
            // 将读取的内容转换成字符串
            sb.append(new String(bytes, 0, length));
        }
        // 关闭流
        fis.close();
        return sb.toString();
    }
}
