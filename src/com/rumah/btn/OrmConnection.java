package com.rumah.btn;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.persistence.Id;
import javax.persistence.IdClass;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.db.H2DatabaseType;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.logger.LocalLog;
import com.rumah.btn.model.BTN_rumah;

public class OrmConnection {
	public static void main(String[] args) {
	}

	static JdbcPooledConnectionSource conn;

	public static Dao<BTN_rumah, String> getRumahBtn() {
		try {
			if (conn == null) {
				conn = getCOnnection();
			}
			return DaoManager.createDao(conn, BTN_rumah.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static void close() {
		try {
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static JdbcPooledConnectionSource getCOnnection() {
		JdbcPooledConnectionSource connectionSource;
		try {
			connectionSource = new JdbcPooledConnectionSource(getData("spring.datasource.url"), new H2DatabaseType());
			connectionSource.setUsername(getData("spring.datasource.username"));
			connectionSource.setPassword(getData("spring.datasource.password"));
			System.setProperty(LocalLog.LOCAL_LOG_LEVEL_PROPERTY, "ERROR");
			return connectionSource;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String getData(String var) {

		try {
			InputStream input = OrmConnection.class.getClassLoader().getResourceAsStream("application.properties");
			Properties prop = new Properties();
			prop.load(input);
			System.out.println(prop.getProperty(var));
			return prop.getProperty(var);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
