package com.chainsys.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Jdbcpst {

	public static void preparestmt(String sql, Object... params) throws SQLException, DBexception {
		try(Connection con = databaseconnection.connect();
		PreparedStatement pst = con.prepareStatement(sql);) {
		int i = 1;
		for (Object obj : params) {
			pst.setObject(i, obj);
			i++;
		}
		pst.executeUpdate();
		} catch (Exception e) {
			throw new DBexception(Errormessage.CONNECTION_FAILURE); 
		}
	}
}
