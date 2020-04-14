package com;

import model.Doctor;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Doctors")
public class DoctorService {
	Doctor d1 = new Doctor();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)

	public String readDoctors() {
		return d1.readDoctors();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDoctors(@FormParam("D_Name") String D_Name, @FormParam("D_Type") String D_Type,
			@FormParam("D_Contact_Number") String D_Contact_Number, @FormParam("D_Address") String D_Address,
			@FormParam("D_Email") String D_Email, @FormParam("Hospital_ID") String Hospital_ID)

	{
		String output = d1.insertDoctors(D_Name, D_Type, D_Contact_Number, D_Address, D_Email, Hospital_ID);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateDoctors(String dData) {
		// Convert the input string to a JSON object
		JsonObject d12 = new JsonParser().parse(dData).getAsJsonObject();
		// Read the values from the JSON object
		String Doctor_ID = d12.get("Doctor_ID").getAsString();
		String D_Name = d12.get("D_Name").getAsString();
		String D_Type = d12.get("D_Type").getAsString();
		String D_Contact_Number = d12.get("D_Contact_Number").getAsString();
		String D_Address = d12.get("D_Address").getAsString();
		String D_Email = d12.get("D_Email").getAsString();
		String Hospital_ID = d12.get("Hospital_ID").getAsString();

		// String output = d1.updateItem(Doctor_ID, D_Name,D_Type ,
		// D_Contact_Number,D_Address,D_Email,Hospital_ID );
		// return output;
		String output = d1.updateDoctors(Doctor_ID, D_Name, D_Type, D_Contact_Number, D_Address, D_Email, Hospital_ID);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteDoctor(String dData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(dData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String Doctor_ID = doc.select("Doctor_ID").text();
		String output = d1.deleteDoctor(Doctor_ID);
		return output;
	}

	// A common method to connect to the DB
	// A common method to connect to the DB
	// A common method to connect to the DB
	// A common method to connect to the DB
	// A common method to connect to the DB
	// A common method to connect to the DB
	// A common method to connect to the DB

}
