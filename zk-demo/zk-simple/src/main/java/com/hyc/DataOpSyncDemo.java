package com.hyc;

import com.hyc.cons.Cons;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 同步操作节点并监听状态变化
 */
public class DataOpSyncDemo implements Watcher {
    private ZooKeeper zooKeeper = null;
    private CountDownLatch connectedSignal = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        DataOpSyncDemo demo = new DataOpSyncDemo();
        demo.operate();
    }

    public void operate() throws Exception {
        try {
            // 连接是异步的
            zooKeeper = new ZooKeeper(Cons.CONNECT_STRING, Cons.SESSION_TIMEOUT, this);
            // 等待连接完成
            connectedSignal.await();

            System.out.println("初始子节点为：");
            List<String> zooChildren = zooKeeper.getChildren("/test", true);
            for (String child : zooChildren) {
                System.out.print(child + ",");
            }

            // 创建节点
            Stat hycStat = zooKeeper.exists("/test/person_hyc", true);
            Stat cxhStat = zooKeeper.exists("/test/person_cxh", true);
            zooKeeper.create("/test/person_hyc", "hyc".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            TimeUnit.SECONDS.sleep(1);
            zooKeeper.create("/test/person_cxh", "cxh".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            TimeUnit.SECONDS.sleep(1);

            // 更新节点
            zooKeeper.setData("/test/person_hyc", "hycUpdate".getBytes(), -1);
            TimeUnit.SECONDS.sleep(1);

            // 删除节点
            zooKeeper.delete("/test/person_hyc", -1);
            zooKeeper.delete("/test/person_cxh", -1);

            while (true) {
                TimeUnit.SECONDS.sleep(3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            releaseConnection();
        }
    }

    private void releaseConnection() {
        if (null != this.zooKeeper) {
            try {
                this.zooKeeper.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void process(WatchedEvent event) {
        try {
            if (event.getType() == Watcher.Event.EventType.None && event.getState() == Event.KeeperState.SyncConnected) {
                // wait在connect方法上的线程将被唤醒
                connectedSignal.countDown();
            } else {
                if (Event.EventType.NodeCreated == event.getType()) {
                    byte[] data = zooKeeper.getData(event.getPath(), true, null);
                    System.out.println("\r\n监听到节点创建事件，path为：" + event.getPath() + "，值为：" + new String(data));
                } else if (Event.EventType.NodeDataChanged == event.getType()) {
                    byte[] data = zooKeeper.getData(event.getPath(), true, null);
                    System.out.println("\r\n监听到节点数据修改事件，path为：" + event.getPath() + "，新值为：" + new String(data));
                } else if (Event.EventType.NodeDeleted == event.getType()) {
                    System.out.println("\r\n监听到节点删除事件，path为：" + event.getPath());
                } else if (Event.EventType.NodeChildrenChanged == event.getType()) {
                    System.out.println("\r\n监听到子节点发生了变化事件，新的子节点为：");
                    List<String> zooChildren = null;
                    zooChildren = zooKeeper.getChildren("/test", true);
                    for (String child : zooChildren) {
                        System.out.print(child + ",");
                    }
                } else if (Event.EventType.DataWatchRemoved == event.getType()) {
                    System.out.println("\r\n监听到节点数据监听移除事件，path为：" + event.getPath());
                } else if (Event.EventType.ChildWatchRemoved == event.getType()) {
                    System.out.println("\r\n监听到节点的子节点监听移除事件，path为：" + event.getPath());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
