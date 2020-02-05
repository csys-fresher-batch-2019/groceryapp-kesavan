package com.chainsys.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Jdbcpst {

	public static void preparestmt(String sql, Object... params) throws Exception {
		Connection con = databaseconnection.connect();
		PreparedStatement pst = con.prepareStatement(sql);
		int i = 1;
		for (Object obj : params) {
			pst.setObject(i, obj);
			i++;
		}
		pst.executeUpdate();
		con.close();
	}
}
