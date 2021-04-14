$(document).ready(function() {
	$('#dataTable').dataTable();

	$("#addPatientForm").validate({
		rules : {
			name : {
				required : true,
				maxlength : 200
			},
			phoneNumber: {
				required : true,
				number: true,
				maxlength : 10
			}
		},
		messages : {
			username : {
				required : "Please enter a name",
				maxlength : "Name should not be more than 200 characters"
			},
			phoneNumber: {
				required : "Please enter Phone number",
				number: "Enter numbers only",
				maxlength : "Phone number should not be more than 10 digits"
			}
		}
	});

	$("#addPatientForm").submit(function(event) {
		event.preventDefault();
		if ($("#addPatientForm").validate().errorList.length == 0) {
			var patient = {};
			patient["id"] = $("#id").val();
			patient["name"] = $("#name").val();
			patient["gender"] = $("#gender").val();
			patient["phoneNumber"] = $("#phoneNumber").val();
			console.log(patient);
			$("#submitButton").prop("disabled", true);
			$.ajax({
				type : "post",
				contentType : "application/json",
				url : "/upsertPatient",
				data : JSON.stringify(patient),
				dataType : 'json',
				cache : false,
				timeout : 600000,
				success : function(data) {
					console.log("SUCCESS : ", e.responseJSON);					
					$("#submitButton").prop("disabled", false);					
				},
				error : function(e) {
					console.log("ERROR : ", e);				
					$("#submitButton").prop("disabled", false);
				}
			});	
			$("#addPatientModal").modal('hide');
			$("#addPatientForm").validate().resetForm();
			resetModal();
			setInterval('location.reload()', 1000);
		}		
	});
	
	$(".editPatient").click(function(){
		$("#id").val($(this).attr("data-id"));
		console.log($("#id").val());
		$("#name").val($(this).attr("data-name"));
		$("#gender").val($(this).attr("data-gender"));
		$("#phoneNumber").val($(this).attr("data-phoneNumber"));
		$("#addPatientModal").modal('show');
	});
	
	$(".deletePatient").click(function(){
		$.ajax({
			type : "delete",
			url : "/delete/"+$(this).attr("data-id"),
			cache : false,
			timeout : 600000,
			success : function(data) {
				console.log("SUCCESS : ", e.responseJSON);
				$("#submitButton").prop("disabled", false);					
			},
			error : function(e) {
				console.log("ERROR : ", e.responseJSON);
				$("#submitButton").prop("disabled", false);
			}
		});
		setInterval('location.reload()', 1000);
	});
	
	$("#closeButton").on('click',function(){
		resetModal();
		$("#addPatientForm").validate().resetForm();
	});
	
	function resetModal(){
		$("#id").val("");
		$("#name").val("");
		$("#gender").val();
		$("#phoneNumber").val("");
	}
});