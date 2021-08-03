package com.logintest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDao {
	
	public String AuthenticateUser (LoginBean loginBean) {
		
		String username = loginBean.getUsername();
		String password = loginBean.getPassword();
		
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		String userNameDb = "";
		String passwordDB = "";
		String roleDB = "";
		
		String sql = "select * from loginroletest";
		
		try {
			
			myConn = DbConnection.createConnection();
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next()) {
				
				userNameDb = myRs.getString("username");
				passwordDB = myRs.getString("password");
				roleDB = myRs.getString("role");
				
				if(username.equals(userNameDb) && password.equals(passwordDB) && roleDB.equals("admin")) {
					return "Admin_role";
				}else if(username.equals(userNameDb) && password.equals(passwordDB) && roleDB.equals("student")) {
					return "Student_role";
				}
					
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "Invalid user credentials";
		
		
		
	}

}
