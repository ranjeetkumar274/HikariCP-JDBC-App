package com.ashu;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionFactory {
	
	private static DataSource datasource = null;
	
	static {
		try {
			
			File f = new File("db.properties");
			FileInputStream fis = new FileInputStream(f);
			
			Properties p = new Properties();
			p.load(fis);
			
			String url = p.getProperty("db.url");
			String uname = p.getProperty("db.uname");
			String pwd = p.getProperty("db.pwd");
			String poolsize = p.getProperty("db.poolsize");
			
			HikariConfig config = new HikariConfig();
			
			config.setJdbcUrl(url);
			config.setPassword(pwd);
			config.setUsername(uname);
			config.setMaximumPoolSize(Integer.parseInt(poolsize));
			
			datasource = new HikariDataSource(config);
			
			
				
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getDBConnection() throws Exception {
		return datasource.getConnection();
	}
}




