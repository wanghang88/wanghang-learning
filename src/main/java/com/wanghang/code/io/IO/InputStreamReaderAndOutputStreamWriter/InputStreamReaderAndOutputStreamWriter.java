package com.wanghang.code.io.IO.InputStreamReaderAndOutputStreamWriter;


import java.io.*;

/**
 *字符流：InputStreamReader、OutputStreamWriter
 *      字符流适用于文本文件的读写，OutputStreamWriter类其实也是借助FileOutputStream类实现的，故其构造方法是FileOutputStream的对象
 *字符流便捷类:FileWriter和FileReader
 *        Java提供了FileWriter和FileReader简化字符流的读写,new FileWriter等同于new OutputStreamWriter(new FileOutputStream(file, true))
 *
 *
 */
public class InputStreamReaderAndOutputStreamWriter {


    public static void main(String[] args) throws IOException {
        File file = new File("D:/test.txt");

        //1:通过OutputStreamWriter写入文件
        write(file);

        //2:通过InputStreamReader读取text.txt文件
        String read = read(file);
        System.out.println("read:"+read);



        //3:通过FileWriter写入文件
        fileWriterWrite(file);

        //4:通过FileReader读取text.txt文件
        String fileReader = fileReaderRead(file);
        System.out.println("fileReader:"+fileReader);
    }


    public static void write(File file) throws IOException {
        // OutputStreamWriter可以显示指定字符集，否则使用默认字符集
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8");

        // 要写入的字符串
        String string = "OutputStreamWriter松下问童子，言师采药去。只在此山中，云深不知处。";
        osw.write(string);
        osw.close();
    }

    public static String read(File file) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
        // 字符数组：一次读取多少个字符
        char[] chars = new char[1024];
        // 每次读取的字符数组先append到StringBuilder中
        StringBuilder sb = new StringBuilder();
        // 读取到的字符数组长度，为-1时表示没有数据
        int length;
        // 循环取数据
        while ((length = isr.read(chars)) != -1) {
            // 将读取的内容转换成字符串
            sb.append(chars, 0, length);
        }
        // 关闭流
        isr.close();
        return sb.toString();
    }



    public static void fileWriterWrite(File file) throws IOException {
        FileWriter fw = new FileWriter(file, true);

        // 要写入的字符串
        String string = "FileWriter松下问童子，言师采药去。只在此山中，云深不知处。";
        fw.write(string);
        fw.close();
    }
    public static String fileReaderRead(File file) throws IOException {
        FileReader fr = new FileReader(file);
        // 一次性取多少个字节
        char[] chars = new char[1024];
        // 用来接收读取的字节数组
        StringBuilder sb = new StringBuilder();
        // 读取到的字节数组长度，为-1时表示没有数据
        int length;
        // 循环取数据
        while ((length = fr.read(chars)) != -1) {
            // 将读取的内容转换成字符串
            sb.append(chars, 0, length);
        }
        // 关闭流
        fr.close();
        return sb.toString();
    }
}
