package com.wanghang.code.io.IO.file;


import java.io.File;
import java.io.IOException;

/**
 *File类：
 *     类是用来操作文件的类，但它不能操作文件中的数据
 *
 */
public class FileDemo {

    public static void main(String[] args) throws IOException {
        File file = new File("D:/wanghang/fileTest.txt");

        //1:判断文件是否存在
        if (!file.exists()) {
            // 不存在则创建
            file.createNewFile();
        }
        boolean fileisDirectory = file.isDirectory();
        System.out.println("file是否问目录:"+fileisDirectory);

        //2:file的目录操作：
        //file1.mkdir():只能创建一级目录，且父目录必须存在，否则无法成功创建一个目录;
        //file1.mkdirs():可以创建多级目录，父目录不一定存在
        File file1 = new File("D:/wanghang/File");
        boolean mkdirBool = file1.mkdir();
        boolean mkdirsBool = file1.mkdirs();
        System.out.println("mkdirBool:"+mkdirBool);
        System.out.println("mkdirsBool:"+mkdirsBool);
        boolean file1Isdirectory = file1.isDirectory();
        System.out.println("file1是否为目录:"+file1Isdirectory);


        System.out.println("文件的绝对路径：" + file.getAbsolutePath());
        System.out.println("文件的大小：" + file.length());

        // 刪除文件
        file.delete();
    }
}
