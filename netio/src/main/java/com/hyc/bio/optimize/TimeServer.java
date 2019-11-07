package com.hyc.bio.optimize;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeServer {
    private static int port = 8080;

    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(5, 20, 10L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("time server is start in port:" + port);

            Socket socket = null;
            while (true) {
                socket = server.accept();
                executor.execute(new TimeServerHandler(socket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                executor.shutdown();
                if (server != null) {
                    System.out.println("time server close");
                    server.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
