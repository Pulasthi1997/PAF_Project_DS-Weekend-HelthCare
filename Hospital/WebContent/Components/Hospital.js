$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateHospitalForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	$("#formHospital").submit();
});
// UPDATE==========================================
$(document).on("click",".btnUpdate",function(event) {
			$("#hidHospitalIDSave").val($(this).closest("tr").find('#hidHospitalIDUpdate').val());
			$("#H_name").val($(this).closest("tr").find('td:eq(0)').text());
			$("#H_contactNumber").val($(this).closest("tr").find('td:eq(1)').text());
			$("#H_address").val($(this).closest("tr").find('td:eq(2)').text());
			$("#H_email").val($(this).closest("tr").find('td:eq(3)').text());
		});
// CLIENTMODEL=========================================================================
function validateHospitalForm() {
	// CODE
	if ($("#H_name").val().trim() == "") {
		return "Insert Hospital name.";
	}
	// NAME
	if ($("#H_contactNumber").val().trim() == "") {
		return "Insert Hospital Contact Number.";
	}
	// PRICE-------------------------------

	if ($("#H_address").val().trim() == "") {
		return "Insert Hospital Address.";
	}
	
	if ($("#H_email").val().trim() == "") {
		return "Insert Hospital email.";
	}
	return true;
}

