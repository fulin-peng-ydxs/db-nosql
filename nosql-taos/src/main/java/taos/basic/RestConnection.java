package taos.basic;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Rest连接
 *
 * @author pengshuaifeng
 * 2024/12/25
 */
public class RestConnection {

    public static void main(String[] args) throws Exception {
        String jdbcUrl = "jdbc:TAOS-RS://10.100.101.104:6041?user=root&password=taosdata";
        try (Connection conn = DriverManager.getConnection(jdbcUrl)) {
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
