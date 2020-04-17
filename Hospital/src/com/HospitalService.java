package com;

import model.Hospital;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Hospital") 

public class HospitalService {
	 Hospital h1 = new Hospital();
	   @GET
	   @Path("/")
	@Produces(MediaType.TEXT_HTML)
	 
	   public String readHospital()
	   {
	   return h1.readHospital();
	   }
	   @POST
	   @Path("/")
	   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	   @Produces(MediaType.TEXT_PLAIN)
	   public String insertHospital(
	    @FormParam("H_name") String H_Name,
	    @FormParam("H_contactNumber") String H_Contact_Number,
	    @FormParam("H_address") String H_address,
	    @FormParam("H_email") String H_email
	 )
	   {
	    String output = h1.insertHospital(H_Name,H_Contact_Number ,H_address , H_email);
	   return output;
	   }
	   @PUT
	   @Path("/")
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Produces(MediaType.TEXT_PLAIN)
	   public String updateHopital(String dData)
	   {
	   
	    JsonObject h2 = new JsonParser().parse(dData).getAsJsonObject();
	    String H_ID = h2.get("H_ID").getAsString();
	    String H_Name = h2.get("H_name").getAsString();
	    String H_contactNumber = h2.get("H_contactNumber").getAsString();
	    String H_address = h2.get("H_address").getAsString();
	    String H_email = h2.get("H_email").getAsString();
	    
	   
	   String output = h1.updateHopital(H_ID, H_Name,H_contactNumber,H_address,H_email);
	   return output;
	   }
	   @DELETE
	   @Path("/")
	   @Consumes(MediaType.APPLICATION_XML)
	   @Produces(MediaType.TEXT_PLAIN)
	   public String deleteHospital(String dData)
	   {
	  
	    Document doc = Jsoup.parse(dData, "", Parser.xmlParser());

	 
	    String H_ID = doc.select("H_ID").text();
	    String output = h1.deleteHospital(H_ID);
	   return output;
	   }

}