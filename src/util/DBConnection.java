package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    public static Connection getConnection() throws ClassNotFoundException {
        if (connection == null) {
            String connectionString = DBPropertyUtil.getPropertyString("db.properties");
            try {
            	Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(connectionString);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
