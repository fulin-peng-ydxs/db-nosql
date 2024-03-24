package zookeeper.apply.server.manager;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;

public class DistributeServer {

	public static void main(String[] args) throws Exception {
		DistributeServer server = new DistributeServer();
		// 1 连接zookeeper集群
		server.getConnect();
		// 2 注册节点
		server.registerServer(args[0]);
		// 3 业务逻辑处理
		server.business();
	}

	private void business() throws InterruptedException {
	
		Thread.sleep(Long.MAX_VALUE);
	}

	private void registerServer(String hostname) throws KeeperException, InterruptedException {
		zkClient.create("/servers/server", hostname.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		System.out.println(hostname +"is online ");
	}

	private final String connectString = "hadoop102:2181,hadoop103:2181,hadoop104:2181";
	private final int sessionTimeout = 2000;
	private ZooKeeper zkClient;

	private void getConnect() throws IOException {
		
		zkClient = new ZooKeeper(connectString , sessionTimeout , event -> {
            // TODO Auto-generated method stub

        });
	}
}
