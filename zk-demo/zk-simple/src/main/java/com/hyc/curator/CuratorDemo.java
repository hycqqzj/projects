package com.hyc.curator;

import com.hyc.cons.Cons;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

public class CuratorDemo {
    private CuratorFramework client;
    private RetryPolicy retryPolicy;

    public CuratorDemo() {
        retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.newClient(Cons.CONNECT_STRING, retryPolicy);
        client.start();
    }

    /**
     * 同步操作
     *
     * @throws Exception
     */
    private void doSyncOp() throws Exception {
        String path = "/one";
        final byte[] data = {'1'};
        client.create().withMode(CreateMode.PERSISTENT).forPath(path, data);

        byte[] actualData = client.getData().forPath(path);
        System.out.println("取出数据：" + new String(actualData));

        client.delete().forPath(path);
    }

    /**
     * 异步操作
     *
     * @throws Exception
     */
    private void doASyncOp() throws Exception {
        String path = "/two";
        final byte[] data = {'2'};
        final CountDownLatch latch = new CountDownLatch(1);

        client.getCuratorListenable().addListener((CuratorFramework c, CuratorEvent event) -> {
            switch (event.getType()) {
                case CREATE:
                    System.out.println("节点：" + event.getPath() + "创建");
                    c.getData().inBackground().forPath(event.getPath());
                    break;
                case GET_DATA:
                    System.out.println("获取到节点：" + event.getPath() + "的值，值为：" + new String(event.getData()));
                    c.delete().inBackground().forPath(path);
                    break;
                case DELETE:
                    System.out.printf("节点：" + event.getPath() + "，被删除");
                    latch.countDown();
                    break;
            }
        });

        client.create().withMode(CreateMode.PERSISTENT).inBackground().forPath(path, data);
        latch.await();
        client.close();
    }

    /**
     * watch
     *
     * @throws Exception
     */
    private void doWatch() throws Exception {
        String path = "/three";
        byte[] data = {'3'};
        byte[] newData = {'4'};
        CountDownLatch latch = new CountDownLatch(1);

        client.getCuratorListenable().addListener(
                (CuratorFramework c, CuratorEvent event) -> {
                    switch (event.getType()) {
                        case WATCHED:
                            WatchedEvent we = event.getWatchedEvent();
                            System.out.println("watched event: " + we);
                            if (we.getType() == Watcher.Event.EventType.NodeDataChanged && we.getPath().equals(path)) {
                                // 4. watch triggered
                                byte[] actualData = c.getData().forPath(path);
                                System.out.println("节点发生了变化，新的值为：" + new String(actualData));
                            }
                            latch.countDown();
                            break;
                    }
                });

        // 1. create
        client.create().withMode(CreateMode.PERSISTENT).forPath(path, data);
        // 2. getData and register a watch
        byte[] actualData = client.getData().watched().forPath(path);

        // 3. setData
        client.setData().forPath(path, newData);
        latch.await();

        // 5. delete
        client.delete().forPath(path);
    }

    /**
     * 监听和异步回调
     *
     * @throws Exception
     */
    private void doCallbackAndWatch() throws Exception {
        String path = "/four";
        byte[] data = {'4'};
        byte[] newData = {'5'};
        CountDownLatch latch = new CountDownLatch(2);

        client.getCuratorListenable().addListener(
                (CuratorFramework c, CuratorEvent event) -> {
                    switch (event.getType()) {
                        case CREATE:
                            System.out.printf("znode '%s' created\n", event.getPath());
                            client.setData().forPath(path, newData);
                            latch.countDown();
                            break;
                        case WATCHED:
                            WatchedEvent we = event.getWatchedEvent();
                            System.out.println("watched event: " + we);
                            if (we.getType() == Watcher.Event.EventType.NodeDataChanged && we.getPath().equals(path)) {
                                System.out.printf("got the event for the triggered watch\n");
                            }
                            latch.countDown();
                            break;
                    }
                });

        client.create().withMode(CreateMode.PERSISTENT).inBackground().forPath(path, data);
        latch.await();
        client.delete().forPath(path);
    }

    public static void main(String[] args) throws Exception {
        CuratorDemo demo = new CuratorDemo();
        demo.doCallbackAndWatch();
    }


}
