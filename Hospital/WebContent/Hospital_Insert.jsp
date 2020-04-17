<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="model.Hospital" %>
    
    
<%	
	if (request.getParameter("H_name") != null) {
		Hospital Hospital_1 = new Hospital();
		String
		stsMsg = "";
		
		//Insert--------------------------
		if (request.getParameter("hidHospitalIDSave") == "") {
			stsMsg = Hospital_1.insertHospital(request.getParameter("H_name"),
					request.getParameter("H_contactNumber"), request
							.getParameter("H_address"), request
							.getParameter("H_email"));
		} else//Update----------------------
		{
			stsMsg = Hospital_1.updateHopital(request.getParameter("hidHospitalIDSave"),
							request.getParameter("H_name"), request
									.getParameter("H_contactNumber"), request
									.getParameter("H_address"), request
									.getParameter("H_email"));
		}
		session.setAttribute("statusMsg", stsMsg);
	}
	//Delete--------------------------------
	if (request.getParameter("hidHospitalIDDelete") != null) {
		Hospital
		Hospital_1 = new Hospital();
		String
		stsMsg = Hospital_1.deleteHospital(request
				.getParameter("hidHospitalIDDelete"));
		session.setAttribute("statusMsg", stsMsg);
	}
%>	
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital_Insert</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="./Components/Hospital.js"></script>
</head>
<body>
	<form id="formHospital" name="formHospital" method="post" action="Hospital_Insert.jsp">
		Hospital Name: 
		<input id="H_name" name="H_name" type="text"
			class="form-control form-control-sm" required> <br>
	    Contact No:
	     <input id="H_contactNumber" name="H_contactNumber" type="text" placeholder="0xxxxxxxxx" maxlength="10"
			 pattern="^\d{10}$" class="form-control form-control-sm"required > <br> 
		Address: 
		<input id="H_address" name="H_address" type="text"
			class="form-control form-control-sm" required> <br> 
		E-mail:
		 <input id="H_email" name="H_email" type="text"  
			class="form-control form-control-sm" required> <br> 
		<input id="btnSave" name="btnSave" type="submit" value="Save" 
			class="btn btn-primary"  > 
		<input type="hidden" id="hidHospitalIDSave" name="hidHospitalIDSave" value="">
	</form>

	<div id"alertSuccess" class="alert alert-success">
		<%
			out.print(session.getAttribute("statusMsg"));
		%>

	</div>

	<%
	 Hospital h1= new Hospital();
	out.print(h1.readHospital());
	%>
		<script language="javascript">


		
</body>
</html>