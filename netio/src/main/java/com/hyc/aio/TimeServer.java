package com.hyc.aio;

public class TimeServer {
    private static int port = 8080;

    public static void main(String[] args) {
        AsyncTimeServerHandler asyncTimeServerHandler = new AsyncTimeServerHandler(port);
        new Thread(asyncTimeServerHandler).start();
    }
}
