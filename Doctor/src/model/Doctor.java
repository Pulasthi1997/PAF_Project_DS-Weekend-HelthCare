package model;

import java.sql.*;

import database.dbconnect;

public class Doctor { // A common method to connect to the DB

	dbconnect obj = new dbconnect();

	public String insertDoctors(String dname, String dtype, String contact, String address, String email,
			String hospitalID) {
		String output = "";

		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for inserting....";
			}
// create a prepared statement
			String query = " insert into hospital_docters (`D_Name`,`D_Type`,`D_Contact_Number`,`D_Address`,`D_Email`,`Hospital_ID`)"
					+ " values ( ?, ?, ?,?,?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values

			preparedStmt.setString(1, dname);
			preparedStmt.setString(2, dtype);
			preparedStmt.setInt(3, Integer.parseInt(contact));
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, hospitalID);

//execute the statement

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully created ";
		} catch (Exception e) {
			output = "Error while inserting the doctor.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readDoctors() {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Doctor Name</th><th>Doctor specification</th><th>"
					+ "Contact Number</th><th>Doctor Address</th><th>Doctor Email </th><th>Doctor hospitalID</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from hospital_docters";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
			while (rs.next()) {
				String Doctor_ID = Integer.toString(rs.getInt("Doctor_ID"));
				String D_Name = rs.getString("D_Name");
				String D_Type = rs.getString("D_Type");
//String itemPrice = Double.toString(rs.getDouble("itemPrice"));
				String D_Contact_Number = Integer.toString(rs.getInt("D_Contact_Number"));
				String D_Address = rs.getString("D_Address");
				String D_Email = rs.getString("D_Email");
				String Hospital_ID = rs.getString("Hospital_ID");

				// Add into the html table
				output += "<tr><td><input id=\"hidDoctorIDUpdate\"name=\"hidDoctorIDUpdate\"type=\"hidden\" value=\""
						+ Doctor_ID + "\">" + D_Name + "</td>";
				output += "<td>" + D_Type + "</td>";
				output += "<td>" + D_Contact_Number + "</td>";
				output += "<td>" + D_Address + "</td>";
				output += "<td>" + D_Email + "</td>";
				output += "<td>" + Hospital_ID + "</td>";
// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-warning btnUpdate\"></td>"
						+ "<td><form method=\"post\" action=\"Doctor_Insert.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
						+ "<input name=\"hidDoctorIDDelete\" type=\"hidden\" value=\"" + Doctor_ID + "\">"
						+ "</form></td></tr>";
			}
			con.close();
//Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the doctors......";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateDoctors(String ID, String dname, String dtype, String contact, String address, String email,
			String hospitalID) {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for updating....";
			}
// create a prepared statement
			String query = "UPDATE hospital_docters SET D_Name=?,D_Type=?,D_Contact_Number=?,D_Address=?,D_Email=?,Hospital_ID=? WHERE Doctor_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setString(1, dname);
			preparedStmt.setString(2, dtype);
			preparedStmt.setInt(3, Integer.parseInt(contact));
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, hospitalID);
			preparedStmt.setInt(7, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully done";
		} catch (Exception e) {
			output = "Error while updating the doctor.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteDoctor(String Doctor_ID) {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
// create a prepared statement
			String query = "delete from hospital_docters where Doctor_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setInt(1, Integer.parseInt(Doctor_ID));
// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the doctor.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}