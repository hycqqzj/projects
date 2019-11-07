package com.hyc.nio.channel;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {
    public static void main(String[] args) throws Exception {
        demo1();
    }

    private static void demo1() throws Exception {
        FileOutputStream fos = new FileOutputStream(new File("G:\\test\\a.txt"));
        FileChannel fileChannel = fos.getChannel();
        ByteBuffer buffer = ByteBuffer.wrap("abcde".getBytes());
        System.out.println("A fileChannel.position()=" + fileChannel.position());
        System.out.println("write 1的返回值：" + fileChannel.write(buffer));
        System.out.println("B fileChannel.position()=" + fileChannel.position());
        fileChannel.position(2);
        buffer.rewind();
        System.out.println("write 2的返回值：" + fileChannel.write(buffer));
        System.out.println("C fileChannel.position()=" + fileChannel.position());

        fos.flush();
        fileChannel.close();

        fos.close();
    }
}
