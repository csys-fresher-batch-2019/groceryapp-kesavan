package com.chainsys.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseconnection {
	public static Connection connect() throws SQLException {
		LoggerGrocery LOGGER = LoggerGrocery.getInstance();
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(ClassNotFoundException e) {
			LOGGER.debug(e);
		}
//192.168.56.208:1521
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "oracle");
	}
}