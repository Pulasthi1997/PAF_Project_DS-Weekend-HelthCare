$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateDoctorForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
 $("#formDoctor").submit();
});

// UPDATE===========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hidDoctorIDSave").val($(this).closest("tr").find('#hidDoctorIDUpdate').val());
 $("#D_Name").val($(this).closest("tr").find('td:eq(0)').text());
 $("#D_Type").val($(this).closest("tr").find('td:eq(1)').text());
 $("#D_Contact_Number").val($(this).closest("tr").find('td:eq(2)').text());
 $("#D_Address").val($(this).closest("tr").find('td:eq(3)').text());
 $("#D_Email").val($(this).closest("tr").find('td:eq(4)').text());
 $("#Hospital_ID").val($(this).closest("tr").find('td:eq(5)').text());
});

// CLIENTMODEL=========================================================================
function validateDoctorForm()
{
// Doctor name
if ($("#D_Name").val().trim() == "")
 {
 return "Insert  Doctor Namee.";
 }
// Doctor type
if ($("#D_Type").val().trim() == "")
 {
 return "Insert Doctor Type.";
 }
// Contact number-------------------------------
if ($("#D_Contact_Number").val().trim() == "")
 {
 return "Insert Contact Number.";
 }
//Address
if ($("#D_Address").val().trim() == "")
 {
 return "Insert Address.";
 }
//Email
if ($("#D_Email").val().trim() == "")
 {
 return "Insert valid Email.";
 }
//Hospital_ID
if ($("#Hospital_ID").val().trim() == "")
 {
 return "Insert Hospital ID.";
 }


return true;
}