package demo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZkTest {

    public static void main(String[] args) throws KeeperException, InterruptedException {

        // ��ȡzookeeper����
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper("172.16.116.100:2181", 30000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (Event.KeeperState.SyncConnected.equals(event.getState()) 
                            && Event.EventType.None.equals(event.getType())) {
                        System.out.println("��ȡ���ӳɹ�������������" + event);
                        countDownLatch.countDown();
                    }
                }
            });

            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ����һ���ڵ㣬1-�ڵ�·�� 2-�ڵ����� 3-�ڵ�ķ���Ȩ�� 4-�ڵ�����
        // OPEN_ACL_UNSAFE���κ��˿��Բ����ýڵ�
        // CREATOR_ALL_ACL��������ӵ�����з���Ȩ��
        // READ_ACL_UNSAFE: �κ��˶����Զ�ȡ�ýڵ�
        // zooKeeper.create("/atguigu/aa", "haha~~".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.create("/test", "haha~~".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        // zooKeeper.create("/atguigu/cc", "haha~~".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        // zooKeeper.create("/atguigu/dd", "haha~~".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        // zooKeeper.create("/atguigu/dd", "haha~~".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        // zooKeeper.create("/atguigu/dd", "haha~~".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        // �жϽڵ��Ƿ����
        Stat stat = zooKeeper.exists("/test", true);
        if (stat != null){
            System.out.println("��ǰ�ڵ���ڣ�" + stat.getVersion());
        } else {
            System.out.println("��ǰ�ڵ㲻���ڣ�");
        }

        // �жϽڵ��Ƿ���ڣ�ͬʱ��Ӽ���
        zooKeeper.exists("/test", event -> {
        });

        // ��ȡһ���ڵ������
        byte[] data = zooKeeper.getData("/atguigu/ss0000000001", false, null);
        System.out.println(new String(data));

        // ��ѯһ���ڵ�������ӽڵ�
        List<String> children = zooKeeper.getChildren("/test", false);
        System.out.println(children);

        // ����
        zooKeeper.setData("/test", "wawa...".getBytes(), stat.getVersion());

        // ɾ��һ���ڵ�
        //zooKeeper.delete("/test", -1);

        if (zooKeeper != null){
            zooKeeper.close();
        }
    }
}
