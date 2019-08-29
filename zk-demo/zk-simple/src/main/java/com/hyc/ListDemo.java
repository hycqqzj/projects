package com.hyc;

import com.hyc.cons.Cons;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;

/**
 * 连接zookeeper并查看根路径的节点
 */
public class ListDemo {

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper(Cons.connectString, 5000, null);

            List<String> zooChildren = zooKeeper.getChildren("/", false);
            for (String child : zooChildren) {
                System.out.println(child);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zooKeeper.close();
        }
    }
}
