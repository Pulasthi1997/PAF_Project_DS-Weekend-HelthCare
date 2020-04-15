
package com;

import model.Users;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/User")
public class UserService_API {
	
	Users userObj = new Users();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUsers() {
		
		return userObj.readUsers(); 
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addUserDetails(@FormParam("User_Name") String User_Name, 
			@FormParam("U_NIC") String U_NIC,
			@FormParam("U_Age") String U_Age, 
			@FormParam("U_Contact_Number") String U_Contact_Number, 
			@FormParam("U_Email") String U_Email,
			@FormParam("U_Address") String U_Address) {
		
		String output = userObj.addUserDetails(User_Name,U_NIC,U_Age,U_Contact_Number,U_Email,U_Address);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUserDetails(String userData) {
		// Convert the input string to a JSON object
		JsonObject UserJsonObject = new JsonParser().parse(userData).getAsJsonObject();
		// Read the values from the JSON object
		String User_ID = UserJsonObject.get("User_ID").getAsString();
		String User_Name = UserJsonObject.get("User_Name").getAsString();
		String U_NIC = UserJsonObject.get("U_NIC").getAsString();
		String U_Age = UserJsonObject.get("U_Age").getAsString();
		String U_Contact_Number = UserJsonObject.get("U_Contact_Number").getAsString();
		String U_Email = UserJsonObject.get("U_Email").getAsString();
		String U_Address = UserJsonObject.get("U_Address").getAsString();
		String output = userObj.updateUserDetails(User_ID, User_Name , U_NIC , U_Age , U_Contact_Number , U_Email , U_Address );
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUsers(String userData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

		// Read the value from the element <userID>
		String User_ID = doc.select("User_ID").text();
		String output = userObj.deleteUsers(User_ID);
		return output;
	}

}
