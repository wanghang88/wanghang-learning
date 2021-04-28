package com.wanghang.code.io.IO.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    public static final int port = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(port);

        System.out.println("开始： " + server);
        try {
            Socket socket = server.accept();
            System.out.println("Connection socket: " + socket);
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);

                while(true) {
                    String str = reader.readLine();
                    if("end".equals(str))
                        break;
                    System.out.println("Echoing: " + str);
                    printWriter.println(str);
                }
            } finally {
                System.out.println("CLosing....");
                socket.close();
            }
        } finally {
            server.close();
        }
    }
}
