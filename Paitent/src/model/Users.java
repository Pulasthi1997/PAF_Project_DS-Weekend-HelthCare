package model;

import java.sql.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Util.DBConnect;

public class Users {

//	private Connection connect() {
//		Connection con = null;
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//
//			// Provide the correct details: DBServer/DBName, username, password
//			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_project?useTimezone=true&serverTimezone=UTC","root", "");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return con;
//	}

	DBConnect obj  = new DBConnect();
	
public String addUserDetails(String User_Name,String U_NIC, String U_Age , String U_Contact_Number, String U_Email, String U_Address) {
	
	String output = "";
	
	try {
		
		Connection con = obj.connect();
		if (con == null) {
			return "Error while connecting to the database for inserting.";
		}
		
		// create a prepared statement
		String query = " insert into user(`User_ID`,`User_Name`,`U_NIC`,`U_Age`,`U_Contact_Number`,`U_Email`,`U_Address`)"
				+ " values (?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		// binding values
		preparedStmt.setInt(1, 0);
		preparedStmt.setString(2,User_Name);
		preparedStmt.setString(3,U_NIC);
		preparedStmt.setInt(4, Integer.parseInt(U_Age));
		preparedStmt.setInt(5, Integer.parseInt(U_Contact_Number));
		preparedStmt.setString(6,U_Email);
		preparedStmt.setString(7, U_Address);
		
		// execute the statement
		preparedStmt.execute();
		con.close();
		output = "Inserted successfully";
		
	} catch (Exception e) {
		
		output = "Error while inserting the user.";
		System.err.println(e.getMessage());
	}
	return output;
}

public String readUsers() {
	
	String output = "";
	try {
		Connection con = obj.connect();
		if (con == null) {
			return "Error while connecting to the database for reading.";
		}
		
		// Prepare the html table to be displayed
		
		output = "<table border=\"1\"><tr><th>User ID</th><th>User Name</th><th>NIC</th><th>Age</th><th>Contact Number</th><th>Email</th><th>Address</th><th>Update</th><th>Remove</th></tr>";
		String query = "select * from user";
		Statement stmt = (Statement) con.createStatement();
		
		ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
		
		// iterate through the rows in the result set
		while (rs.next()) {
			String User_ID = Integer.toString(rs.getInt("User_ID"));
			String User_Name = rs.getString("User_Name");
			String U_NIC = rs.getString("U_NIC");
			String U_Age = Integer.toString(rs.getInt("U_Age"));
			String U_Contact_Number = Integer.toString(rs.getInt("U_Contact_Number"));
			String U_Email = rs.getString("U_Email");
			String U_Address = rs.getString("U_Address");
			
			
			// Add into the html table
			output += "<tr><td>" + User_ID + "</td>";
			output += "<td>" +User_Name+ "</td>";
			output += "<td>" +U_NIC+ "</td>";
			output += "<td>" +  U_Age + "</td>";
			output += "<td>" + U_Contact_Number + "</td>";
			output += "<td>" + U_Email+ "</td>";
			output += "<td>" + U_Address + "</td>";
			
			
			// buttons
			output += "<td><input name=\"Update_btn\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
					+ "<td><form method=\"post\" action=\"items.jsp\">"
					+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
					+ "<input name=\"userID\" type=\"hidden\" value=\"" + User_ID + "\">" + "</form></td></tr>";
		}
		con.close();
		// Complete the html table
		output += "</table>";
	} catch (Exception e) {
		output = "Error while reading the users.";
		System.err.println(e.getMessage());
	}
	return output;
}

public String updateUserDetails(String User_ID, String User_Name,String U_NIC, String  U_Age , String U_Contact_Number, String U_Email, String U_Address) {
	String output = "";
	try {
		Connection con = obj.connect();
		if (con == null) {
			return "Error while connecting to the database for updating.";
		}
		// create a prepared statement
		String query = "UPDATE user SET User_Name=?,U_NIC=?,U_Age=?,U_Contact_Number=?,U_Email=?,U_Address=?WHERE User_ID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		
		preparedStmt.setString(1, User_Name);
		preparedStmt.setString(2, U_NIC);
		preparedStmt.setInt(3, Integer.parseInt(U_Age));
		preparedStmt.setInt(4, Integer.parseInt(U_Contact_Number));
		preparedStmt.setString(5, U_Email);
		preparedStmt.setString(6, U_Address);
		preparedStmt.setInt(7, Integer.parseInt(User_ID));
		// execute the statement
		preparedStmt.execute();
		con.close();
		output = "Updated successfully";
	} catch (Exception e) {
		output = "Error while updating the user.";
		System.err.println(e.getMessage());
	}
	return output;
}


public String deleteUsers(String User_ID) {
	String output = "";
	try {
		Connection con = obj.connect();
		if (con == null) {
			return "Error while connecting to the database for deleting.";
		}
		// create a prepared statement
		String query = "delete from user where User_ID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		preparedStmt.setInt(1, Integer.parseInt(User_ID));
		// execute the statement
		preparedStmt.execute();
		con.close();
		output = "Deleted successfully";
	} catch (Exception e) {
		output = "Error while deleting the user.";
		System.err.println(e.getMessage());
	}
	return output;
}

}