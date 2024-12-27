package taos.basic;


import com.taosdata.jdbc.TSDBDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 原生连接
 * @author pengshuaifeng
 * 2024/12/25
 */
public class BasicConnection {

    public static void main(String[] args) throws Exception {
        // use
        String jdbcUrl = "jdbc:TAOS://10.100.101.104:6030?user=root&password=taosdata";
        Properties connProps = new Properties();
        connProps.setProperty(TSDBDriver.PROPERTY_KEY_CHARSET, "UTF-8");
        connProps.setProperty(TSDBDriver.PROPERTY_KEY_LOCALE, "en_US.UTF-8");
        connProps.setProperty(TSDBDriver.PROPERTY_KEY_TIME_ZONE, "UTC-8");

        try (Connection conn = DriverManager.getConnection(jdbcUrl, connProps)) {
            System.out.println("Connected to " + jdbcUrl + " successfully.");
            // you can use the connection for execute SQL here
        } catch (Exception ex) {
            // please refer to the JDBC specifications for detailed exceptions info
            System.out.printf("Failed to connect to %s, %sErrMessage: %s%n",
                    jdbcUrl,
                    ex instanceof SQLException ? "ErrCode: " + ((SQLException) ex).getErrorCode() + ", " : "",
                    ex.getMessage());
            // Print stack trace for context in examples. Use logging in production.
            ex.printStackTrace();
            throw ex;
        }
    }
}
