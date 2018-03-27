package com.hnzy.hot.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseUtil {
public static Connection getConnection() throws SQLException{
	Connection conn=null;
	try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url="jdbc:sqlserver://localhost:1433;databaseName=DB_Znsbf";
		String userName="sa";
		String password="sa";
			conn=DriverManager.getConnection(url,userName,password);
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	return conn;
}
public static void closeConn(Connection conn){
	if(conn!=null){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
}
