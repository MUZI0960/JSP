package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Factory Object[Method] Pattern
 */
public class ConnectionFactory {
	private static String url;
	private static String user;
	private static String password;
	private static DataSource dataSource;

	static {
		String path = "/kr/or/ddit/db/dbInfo.properties";
		try(
			InputStream is = ConnectionFactory.class.getResourceAsStream(path);
		){
			Properties dbInfo = new Properties();
			dbInfo.load(is);
			String driverClassName = dbInfo.getProperty("driverClassName");
			url = dbInfo.getProperty("url");
			user = dbInfo.getProperty("user");
			password = dbInfo.getProperty("password");
			
			BasicDataSource bds = new BasicDataSource();
			dataSource = bds;
			bds.setDriverClassName(driverClassName);
			bds.setUrl(url);
			bds.setUsername(user);
			bds.setPassword(password);
//			pooling policy
			// Connection 초기 설정
			bds.setInitialSize(Integer.parseInt(dbInfo.getProperty("initialSize")) );
			bds.setMaxWaitMillis(Long.parseLong(dbInfo.getProperty("maxWait")));
			bds.setMaxTotal(Integer.parseInt(dbInfo.getProperty("maxTotal")));
			
//			try {
//				Class.forName(driverClassName);
//			} catch (ClassNotFoundException e) {
//				throw new RuntimeException(e);
//			}
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Connection getConnection() throws SQLException {
//		Connection conn = DriverManager.getConnection(url, user, password);
		Connection conn = dataSource.getConnection();
		return conn;
	}
	
}
