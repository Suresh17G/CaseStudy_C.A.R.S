package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {
	public static String getPropertyString(String fileName) {
		
		Properties properties = new Properties();
		String connectionString = null;
		try (FileInputStream input = new FileInputStream(fileName)) {
			properties.load(input);
			String host = properties.getProperty("host");
	        String port = properties.getProperty("port");
			String dbname = properties.getProperty("dbname");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			connectionString = "jdbc:mysql://" + host + ":" + port + "/" + dbname + "?user=" + username + "&password=" + password;
			return connectionString;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
