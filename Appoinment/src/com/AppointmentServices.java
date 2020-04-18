package com;

import model.*;

import java.sql.Date;
import java.sql.Time;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.*;

@Path("/appointment")
public class AppointmentServices {

	Appointment appObj = new Appointment();
	@GET
	@Path("/")
	@Produces({ MediaType.TEXT_HTML })
	public String GetAppointment() {
		return appObj.GetAllAppoinments();
	}
	
	//View appointment by passing a appointment ID as parameter
	@GET
	@Path("/single")
	@Produces({ MediaType.TEXT_HTML })
	@Consumes(MediaType.APPLICATION_JSON)
	public String GetSingleAppointment(String id) {
		JsonObject AppObject = new JsonParser().parse(id).getAsJsonObject();
		String appointmentID = AppObject.get("appointmentID").getAsString();
		return appObj.GetSAppointments(appointmentID);
	}
	
	
	
	//Inserting appointment details
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppointment(@FormParam("date") String date, @FormParam("time") String time,
			@FormParam("patientID") int patientID, @FormParam("doctorID") int doctorID,
			@FormParam("paymentID") int paymentID) {
		String output = appObj.insertAppoinment(date, time, patientID, doctorID, paymentID);
		return output;
	}
	
	//Updating appointment details
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAppointment(String AppData) {
		JsonObject AppointmentObj = new JsonParser().parse(AppData).getAsJsonObject();
		String appointmentID = AppointmentObj.get("appointmentID").getAsString();
		String date = AppointmentObj.get("date").getAsString();
		String time = AppointmentObj.get("time").getAsString();
		String patientID = AppointmentObj.get("patientID").getAsString();
		String doctorID = AppointmentObj.get("doctorID").getAsString();
		String paymentID = AppointmentObj.get("paymentID").getAsString();
		String appointmentStatus = AppointmentObj.get("appointmentStatus").getAsString();

		String output = appObj.updateAppoinment(appointmentID, date, time, patientID, doctorID, paymentID,
				appointmentStatus);
		return output;
	}

	//Deleting appointment
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String DeleteAppoinment(String ApplicationID) {

		Document doc = Jsoup.parse(ApplicationID, "", Parser.xmlParser());

		String AppId = doc.select("AppointmentID").text();
		String output = appObj.DeleteAppoinment(AppId);
		return output;
	}

}

