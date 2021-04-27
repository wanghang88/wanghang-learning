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

        // 判断文件是否存在
        if (!file.exists()) {
            // 不存在则创建
            file.createNewFile();
        }
        System.out.println("文件的绝对路径：" + file.getAbsolutePath());
        System.out.println("文件的大小：" + file.length());

        // 刪除文件
        file.delete();
    }
}
