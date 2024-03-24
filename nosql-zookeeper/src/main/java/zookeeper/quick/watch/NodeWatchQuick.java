package zookeeper.quick.watch;


import org.apache.zookeeper.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * 节点操作快速开始
 *
 * @author pengshuaifeng
 * 2024/3/23
 */
public class NodeWatchQuick {

    private ZooKeeper zkClient;

    @Before
    public void init() throws IOException {
        int sessionTimeout = 2000;
        String connectString = "linux1:2181,linux2:2181,linux3:2181";
        zkClient = new ZooKeeper(connectString, sessionTimeout, event -> {
            System.out.println("---------watch----------");
            try {
                //默认启动时就会执行一次：None---null
                if (event.getType() != Watcher.Event.EventType.None) {
                    System.out.println(event.getType() + "---" + event.getPath());
                    getData(true);
                }
            } catch (KeeperException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void watch() throws KeeperException, InterruptedException{
        getData(true);
        Thread.sleep(Long.MAX_VALUE);
    }

    //获取子节点并监控节点变化
    private void getData(boolean watch) throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/fulin", watch);
        for (String child : children) {
            System.out.println(child);
        }
    }
}
