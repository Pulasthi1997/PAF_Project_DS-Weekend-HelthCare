package model;

import java.sql.*;

public class Pay {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertPayment(String paytype, String cno, Date expdate, String code, String AppointmentId)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			// create a prepared statement

			String query = "insert into payment (`Pay_type`,`Pay_cno`,`Pay_expdate`,`Pay_code`,`Appointment_Id`)" + " values (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, paytype);
			preparedStmt.setDouble(2, Double.parseDouble(cno));
			preparedStmt.setDate(3, expdate);
			preparedStmt.setInt(4, Integer.parseInt(code));
			preparedStmt.setString(5, AppointmentId);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}

		catch (Exception e)
		{
			output = "Error while inserting the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPayment()
	{
		String output = "";
		
		try
		{

			Connection con = connect();
	 
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Payment Type</th><th>Card Number</th><th>Expiration</th><th>Security Code</th><th>Appointment ID</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from payment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				String pay_ID = Integer.toString(rs.getInt("pay_ID"));
				String pay_type = rs.getString("pay_type");
				String pay_cno = Double.toString(rs.getDouble("pay_cno"));
				Date pay_exp_date = rs.getDate("pay_exp_date");
				String pay_code = rs.getString("pay_code");
				String Appointment_ID = rs.getString("Appointment_ID");
	 
				// Add into the html table
				output += "<tr><td> <input id=\"hidPaymentIDUpdate\"name=\"hidPaymentIDUpdate\"type=\"hidden\" value=\"" + pay_ID + "\">" + "</td>";
				output += "<td>" + pay_type + "</td>";
				output += "<td>" + pay_cno + "</td>";
				output += "<td>" + pay_exp_date + "</td>";
				output += "<td>" + pay_code + "</td>";
				output += "<td>" + Appointment_ID + "</td>";
				// buttons
				
				output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>" 
						+ "<td><form method=\"post\" action=\"items.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"
						+ "<input name=\"itemID\" type=\"hidden\" value=\"" + pay_ID
						+ "\">" + "</form></td></tr>";
			}

			con.close();
			// Complete the html table
			
			output += "</table>";
	}
		catch (Exception e)
		{
			output = "Error while reading the payments.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePayment(String payID, String paytype, String cno, Date expdate, String code, String AppointmentId)
	{
		
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
	 
			// create a prepared statement
			String query = "UPDATE payment SET pay_type=?,pay_cno=?,pay_exp_date=?,pay_code=?,Appointment_ID WHERE payID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
	 
			// binding values
			preparedStmt.setString(1, paytype);
			preparedStmt.setDouble(2, Double.parseDouble(cno));
			preparedStmt.setDate(3, expdate);
			preparedStmt.setInt(4, Integer.parseInt(code));
			preparedStmt.setString(5, AppointmentId);
			preparedStmt.setInt(6, Integer.parseInt(payID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Updated successfully";
	}
	
		catch (Exception e)
		{
			output = "Error while updating the payment.";
			System.err.println(e.getMessage());
		}
		
		return output;
	 
	}

	public String deletePayment(String pay_ID) {
		
		String output = "";
		
		try
		{
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			
			// create a prepared statement
			String query = "delete from items where pay_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(pay_ID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
			
		}
		
		catch (Exception e) {
			output = "Error while deleting the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
