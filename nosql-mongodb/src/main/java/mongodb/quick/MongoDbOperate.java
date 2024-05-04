package mongodb.quick;


import com.mongodb.client.ListDatabasesIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * mongodb快速开始
 *
 * @author pengshuaifeng
 * 2024/5/3
 */
public class MongoDbOperate {

    public static void main(String[] args) {
        //获取连接
        MongoClient client = MongoDbConnection.getClientByString();
        //查看所有数据库
        ListDatabasesIterable<Document> documents = client.listDatabases();
        for (Document document : documents) {
            System.out.println(document);
        }
        client.close();
    }

}
