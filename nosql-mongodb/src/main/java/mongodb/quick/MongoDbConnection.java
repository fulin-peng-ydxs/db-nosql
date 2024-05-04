package mongodb.quick;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import java.util.Collections;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 获取mongodb连接
 *
 * @author pengshuaifeng
 * 2024/5/3
 */
public class MongoDbConnection {

    public static void main(String[] args) {
        MongoClient client = getClientByCustom();
        MongoClient client1 = getClientByString();
    }

    public static MongoClient getClientByString(){
//        ConnectionString connectionString = new ConnectionString("mongodb://host1:27017,host2:27017,host3:27017/");
        ConnectionString connectionString = new ConnectionString("mongodb://linux1:27017");
        return MongoClients.create(connectionString);
    }


    public static MongoClient getClientByCustom(){
        ServerAddress seed1 = new ServerAddress("linux1", 27017);
//        ServerAddress seed2 = new ServerAddress("host2", 27017);
//        ServerAddress seed3 = new ServerAddress("host3", 27017);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyToClusterSettings(builder ->
//                        builder.hosts(Arrays.asList(seed1, seed2, seed3)))
                        builder.hosts(Collections.singletonList(seed1)))  //设置连接主机
                .applyToConnectionPoolSettings(builder ->
                        builder.maxWaitTime(10, SECONDS) //设置连接池：线程最多等待10SECONDS 获取可用连接
                                .maxSize(200)).build(); ////设置连接池：连接池最大有200个连接
        return MongoClients.create(settings);
    }

}
