package com.hyc.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(9091);
            while (true) {
                DatagramPacket hasRecv = new DatagramPacket(new byte[10], 10);
                socket.receive(hasRecv);
                String str = new String(hasRecv.getData(), 0, hasRecv.getLength(), "UTF-8");
                System.out.println("接收到：" + str);

                byte[] data = "Hello too!".getBytes();
                DatagramPacket toSend = new DatagramPacket(data, data.length, hasRecv.getAddress(), hasRecv.getPort());
                System.out.println("将要发送：" + "Hello too!");
                socket.send(toSend);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}
