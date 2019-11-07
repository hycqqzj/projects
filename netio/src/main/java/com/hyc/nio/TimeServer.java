package com.hyc.nio;

public class TimeServer {
    private static int port = 8080;

    public static void main(String[] args) {
        MultiplexerTimeServer server = new MultiplexerTimeServer(port);
        new Thread(server).start();
    }
}
