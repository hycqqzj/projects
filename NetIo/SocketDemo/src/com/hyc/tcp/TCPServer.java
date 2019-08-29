package com.hyc.tcp;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(9090);
            while (true) {
                Socket socket = ss.accept();
                PrintStream ps = new PrintStream(socket.getOutputStream());
                ps.println("Hello");

                ps.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
