package com.hyc.commonops;

import com.hyc.cons.Cons;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 连接zookeeper并查看和监控根路径的节点
 */
public class ListDemo implements Watcher {
    private ZooKeeper zooKeeper = null;
    private CountDownLatch connectedSignal = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ListDemo demo = new ListDemo();
        demo.listRoot();
    }

    public void listRoot() throws Exception {
        try {
            // 连接是异步的
            zooKeeper = new ZooKeeper(Cons.CONNECT_STRING, Cons.SESSION_TIMEOUT, this);
            // 等待连接完成
            connectedSignal.await();

            System.out.println("初始子节点为：");
            List<String> zooChildren = zooKeeper.getChildren("/", true);
            for (String child : zooChildren) {
                System.out.print(child + ",");
            }

            while (true) {
                TimeUnit.SECONDS.sleep(3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zooKeeper.close();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        try {
            if (event.getState() == Event.KeeperState.SyncConnected) {
                // wait在connect方法上的线程将被唤醒
                connectedSignal.countDown();
            }

            if (Event.EventType.NodeChildrenChanged == event.getType()) {
                System.out.println("\r\n子节点发生了变化，新的子节点为：");
                List<String> zooChildren = null;
                zooChildren = zooKeeper.getChildren("/", true);
                for (String child : zooChildren) {
                    System.out.print(child + ",");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
