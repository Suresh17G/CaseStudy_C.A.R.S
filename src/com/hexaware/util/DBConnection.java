package com.hexaware.util;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static Connection connection;

    public static Connection getConnection() throws ClassNotFoundException {
        if (connection == null) {
            String connectionString = DBPropertyUtil.getPropertyString("db.properties");
            try {
            	Properties properties = new Properties();
            	FileInputStream input = new FileInputStream("db.properties");
    			properties.load(input);
    			String driver = properties.getProperty("driver");
            	Class.forName(driver);
                connection = DriverManager.getConnection(connectionString);
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
