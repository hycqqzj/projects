package com.hyc.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(0);//指定0让操作系统指定一个端口号
            socket.setSoTimeout(10000);//定义超时时间
            InetAddress ip = InetAddress.getByName("127.0.0.1");
            byte[] sendbuff = "hello".getBytes();
            System.out.println("将要发送：长度-" + sendbuff.length + "，内容-" + "hello");

            DatagramPacket send = new DatagramPacket(sendbuff, sendbuff.length, ip, 9091);//用于发送的包
            socket.send(send);

            DatagramPacket recv = new DatagramPacket(new byte[10], 10);//用于接收的包
            socket.receive(recv);
            String res = new String(recv.getData(), 0, recv.getLength(), "UTF-8");
            System.out.println("接收到：" + res);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}
