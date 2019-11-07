package com.hyc.nio;

public class TimeClient {
    private static int port = 8080;

    public static void main(String[] args) {
        new Thread(new TimeClientHandler("127.0.0.1", port)).start();
    }
}
