package com.wanghang.code.io.IO.network;

import java.io.*;
import java.net.Socket;

public class SocketClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8080);
        try {
            System.out.println("Socket = " +socket);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);

            for(int i = 0; i < 10; i++) {
                printWriter.println("hello " + i);
                String str = reader.readLine();
                System.out.println(str);
            }
            printWriter.println("end");
        } finally {
            System.out.println("Closing socket...");
            socket.close();
        }
    }

}
