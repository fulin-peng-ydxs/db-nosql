package zookeeper.quick.node;


import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
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
public class NodeOperateQuick {

    private ZooKeeper zkClient;

    @Before
    public void init() throws IOException {
        int sessionTimeout = 2000;
        String connectString = "linux1:2181,linux2:2181,linux3:2181";
        zkClient = new ZooKeeper(connectString, sessionTimeout,null);
    }

    //创建节点
    @Test
    public void createNode() throws KeeperException, InterruptedException{
        // 1-节点路径 2-节点内容 3-节点的访问权限 4-节点类型
        // OPEN_ACL_UNSAFE：任何人可以操作该节点
        // CREATOR_ALL_ACL：创建者拥有所有访问权限
        // READ_ACL_UNSAFE: 任何人都可以读取该节点
        String path = zkClient.create("/fulin", "dahaigezuishuai".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(path);
    }

    //节点是否存在
    @Test
    public void exist() throws KeeperException, InterruptedException{
        // 1-节点路径 2-是否监控该节点 3-节点的状态
        Stat stat = zkClient.exists("/atguigu", false);
        System.out.println(stat==null? "not exist":"exist");
    }

    @Test
    public void watch() throws KeeperException, InterruptedException{
        Thread.sleep(Long.MAX_VALUE);
    }

    private void getData(boolean watch) throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/fulin", watch);
        for (String child : children) {
            System.out.println(child);
        }
    }
}
