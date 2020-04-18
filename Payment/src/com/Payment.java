package com;

	import model.Pay;

	import java.sql.Date;
	import java.text.SimpleDateFormat;

//For REST Service
	import javax.ws.rs.*;
	import javax.ws.rs.core.MediaType;
	//For JSON
	import com.google.gson.*;
	//For XML
	import org.jsoup.*;
	import org.jsoup.parser.*;
	import org.jsoup.nodes.Document;

	@Path("/Payment")
	
	public class Payment {

		Pay p1 = new Pay();

		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)

		public String readPayment() {
			return p1.readPayment();
		}

		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertPayment(@FormParam("pay_type") String pay_type, @FormParam("pay_cno") String pay_cno,
				@FormParam("pay_exp_date") Date pay_exp_date, @FormParam("pay_code") String pay_code,
				@FormParam("Appointment_Id") String Appointment_Id)

		{
			String output = p1.insertPayment(pay_type, pay_cno, pay_exp_date, pay_code, Appointment_Id);
			return output;
		}

		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updatePayment(String payData) {
			
			// Convert the input string to a JSON object
			JsonObject p12 = new JsonParser().parse(payData).getAsJsonObject();
			
			
			// Read the values from the JSON object
			String payID = p12.get("payID").getAsString();
			String pay_type = p12.get("pay_type").getAsString();
			String pay_cno = p12.get("pay_cno").getAsString();
			//Date format should be 2015-01-01
			Date pay_exp_date = Date.valueOf(p12.get("sds").getAsString());  
			String pay_code = p12.get("pay_code").getAsString();
			String Appointment_Id = p12.get("Appointment_Id").getAsString();

		
			String output = p1.updatePayment(payID, pay_type, pay_cno, pay_exp_date, pay_code, Appointment_Id);
			return output;
		}

		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deletePayment(String payData) {
			// Convert the input string to an XML document
			Document pay = Jsoup.parse(payData, "", Parser.xmlParser());

			// Read the value from the element <itemID>
			String pay_ID = pay.select("pay_ID").text();
			String output = p1.deletePayment(pay_ID);
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
