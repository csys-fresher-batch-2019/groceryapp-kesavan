package com.chainsys.Util;

import java.sql.Connection;
import java.sql.DriverManager;

public class databaseconnection {
	public static Connection connect() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connections = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "oracle");
		return connections;
//192.168.56.208:1521
	}
}