package com.user.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JdbcUtil {
	
	public static void main(String[] args) throws Exception {
		Connection conn = getDBConnection();
		System.out.println(conn);

	}
	public static Connection getDBConnection() throws Exception{
		Properties ps = new Properties();
		InputStream in = JdbcUtil.class.getResourceAsStream("/db.properties");
		ps.load(in);
		String driverClass = ps.getProperty("driverClass");
		String url = ps.getProperty("url");
		String username = ps.getProperty("username");
		String pwd = ps.getProperty("pwd");
		Class.forName(driverClass);
		
		return DriverManager.getConnection(url, username, pwd);
		
	}
}
