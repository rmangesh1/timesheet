/*$(function () {

	var rules = {
		    	rules: {
					name: {
						minlength: 2,
						required: true
					},
					email: {
						required: true,
						email: true
					},
					subject: {
						minlength: 2,
						required: true
					},
					message: {
						minlength: 2,
						required: true
					},
			      validateSelect: {
			      	required: true
		      	},
			      validateCheckbox: {
			      	required: true,
			      	minlength: 2	
		      	  },
			      validateRadio: {
			      	required: true
			      }
				}
		    };
		
	    var validationObj = $.extend (rules, Application.validationRules);
	    
		$('#activity-form').validate(validationObj);
		$('#grade-form').validate(validationObj);
		$('#role-form').validate(validationObj);
		$('#department-form').validate(validationObj);
		$('#activityMapping-form').validate(validationObj);
		$("#organization-form").validate(validationObj);
		$("#project-form").validate(validationObj);
		$("#employee-form").validate(validationObj);
		$("#search-form").validate(validationObj);
		
		
});*/

function initValidation() {
	var rules = {
	    	rules: {
				name: {
					minlength: 2,
					required: true
				},
				email: {
					required: true,
					email: true
				},
				subject: {
					minlength: 2,
					required: true
				},
				message: {
					minlength: 2,
					required: true
				},
		      validateSelect: {
		      	required: true
	      	},
		      validateCheckbox: {
		      	required: true,
		      	minlength: 2	
	      	  },
		      validateRadio: {
		      	required: true
		      }
			}
	    };
	
    var validationObj = $.extend (rules, Application.validationRules);
    
	$('#activity-form').validate(validationObj);
	$('#grade-form').validate(validationObj);
	$('#role-form').validate(validationObj);
	$('#department-form').validate(validationObj);
	$('#activityMapping-form').validate(validationObj);
	$("#organization-form").validate(validationObj);
	$("#project-form").validate(validationObj);
	$("#employee-form").validate(validationObj);
	$("#search-form").validate(validationObj);
	$("#timesheet-form").validate(validationObj);
	
}