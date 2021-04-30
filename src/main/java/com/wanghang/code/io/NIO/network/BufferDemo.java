package com.wanghang.code.io.NIO.network;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *1)Buffer读写数据,一般遵循以下四个步:
 *     当向Buffer写入数据时，Buffer会记录下写了多少数据。一旦要读取数据，需要通过flip() 方法将Buffer从写模式切换到读模式,
 *     在读模式下,可以读取之前写入到 Buffer 的所有数据，一旦读完了所有的数据，就需要清空缓冲区，让它可以再次被写入.
 *
 *1.1)写入数据到Buffer:
 *                 数据从Channel到Buffer：channel.read(byteBuffer),
 *                 数据从Client到Buffer：byteBuffer.put(...),
 *
 *1.2)调用flip()方法(切换到读模式)
 *1.3)从Buffer中读取数据:
 *                 数据从Buffer到Channel：channel.write(byteBuffer),
 *                 数据从Buffer到Server：byteBuffer.get(...),
 *
 *1.4)调用clear()方法或者compact()方法。
 *
 *
 *2)Buffer的api
 *
 *buffer.hasRemaining():方法用于判断当前位置和限制之间是否有任何元素,当且仅当此之后中至少剩余一个元素时，此方法才会返回true
 *                      此缓冲区中没有剩余元素,则返回false;
 *
 *buffer.flip():将limit设置为position，然后position重置为0，返回对缓冲区的引用,
 *buffer.clear():清空调用缓冲区并返回对缓冲区的引用.
 *
 *
 *
 *3)capacity,position,limit:
 * 缓冲区本质上是一块可以写入数据，然后可以从中读取数据的内存,这块内存被包装成NIO Buffer对象,并提供了一组方法，用来方便的访问该块内存.
 * capacity:缓冲区的大小,你只能往里写capacity个byte、long，char等类型。一旦Buffer满了，需要将其清空（通过读数据或者清除数据）才能继续写数据往里写数据,
 * position:当前位置，是缓冲区中下一次发生读取和写入操作的索引，当前位置通过大多数读写操作向前推进,
 * limit:界限，是缓冲区中最后一个有效位置之后下一个位置的索引
 *
 */
public class BufferDemo {

    public static void main(String[] args) throws IOException {
        //1:准备数据
        dataReady();

        File sorceFile=new File("D:/wanghang/data.txt");
        FileInputStream inputStream=new FileInputStream(sorceFile);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        FileChannel channel = inputStream.getChannel();
        int read = channel.read(byteBuffer);

        while (read!=-1){
            byteBuffer.flip(); //切换到写模式
            while(byteBuffer.hasRemaining()){
                System.out.print((char) byteBuffer.get()); // read 1 byte at a time
            }
            byteBuffer.clear(); //make buffer ready for writing

            read = channel.read(byteBuffer);
        }

        inputStream.close();
    }










    //准备数据：D:/wanghang/data.txt
    public static void dataReady() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000000; i++) {
            sb.append("abcdefghijklmnopqrstuvwxyz");
        }
        File file = new File("D:/wanghang/data.txt");
        OutputStream os = new FileOutputStream(file);
        os.write(sb.toString().getBytes());
        os.close();
        System.out.println("完毕");
    }
}
