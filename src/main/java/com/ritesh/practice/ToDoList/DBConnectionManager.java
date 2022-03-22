package com.ritesh.practice.ToDoList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**	
 * This is a DataBase connection class
 * @return the connection variable after the successful database connection 
 * 
 */

public class DBConnectionManager {

	{
		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/todoapp?autoReconnect=true&useSSL=false";
		String username = "root";
		String password = "********";

		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

}
