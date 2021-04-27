package com.wanghang.code.io.IO.BufferedReaderAndBufferedWriter;


import java.io.*;

/**
 *字符缓冲流:BufferedReader,BufferedWriter
 *
 *
 */
public class BufferedReaderAndBufferedWriterDemo {


    public static void main(String[] args) throws IOException {

        File file = new File("D:/wanghang/test.txt");
        //1:通过BufferedOutputStream写入文件
        write(file);


        //2:通过BufferedInputStream读取text.txt文件
        String read = read(file);
        System.out.println("read:"+read);
    }



    public static void write(File file) throws IOException {
        // BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new
        // FileOutputStream(file, true), "UTF-8"));
        // FileWriter可以大幅度简化代码
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

        // 要写入的字符串
        String string = "松下问童子，言师采药去。只在此山中，云深不知处。";
        bw.write(string);
        bw.close();
    }


    public static String read(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        // 用来接收读取的字节数组
        StringBuilder sb = new StringBuilder();

        // 按行读数据
        String line;
        // 循环取数据
        while ((line = br.readLine()) != null) {
            // 将读取的内容转换成字符串
            sb.append(line);
        }
        // 关闭流
        br.close();
        return sb.toString();
    }
}
