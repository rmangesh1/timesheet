<html lang="en" data-ng-app="emsApp"><head>
    <meta charset="utf-8">
    <title>Employee management system</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes"> 
    
	<jsp:include page="./pagecss.jsp"></jsp:include>
	<script src="./js/kendo.all.min.js"></script>	
	 <script src="./js/login.js"></script>
	 <script src="./js/search.js"></script>
	 <script src="./js/employee.js"></script>
	 <script src="./js/grade.js"></script>
	 <script src="./js/organization.js"></script>
	 <script src="./js/project.js"></script>
	 <script src="./js/activities.js"></script>
	 <script src="./js/activityMapping.js"></script>
	 <script src="./js/tsFactorMaster.js"></script>
	 <script src="./js/departments.js"></script>
	 <script src="./js/roles.js"></script>
	  <script src="./js/viewSubordinates.js"></script>
	 <script src="./js/viewTimesheet.js"></script>
	 <script src="./js/timesheet.js"></script>
	 <script src="./js/employeeProfile.js"></script>
	 <script src="./js/employeeView.js"></script>
	 <script src="./js/emsapp.js"></script>
	

</head>

<body>
	<div data-ng-view></div>
	
	
	
	<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="./js/jquery-ui-1.10.0.custom.min.js"></script>
<script src="./js/bootstrap.min.js"></script>
<script src="./js/jquery.validate.js"></script>
<script src="./js/Application.js"></script>
<!-- <script src="./js/signin.js"></script> -->
<script src="./js/validation.js"></script>

<script>
/* 
$(function () {

	// Validate the form on load
	//$('#validation-form').submit ();

	// Block the form from submitting
	$('form').submit (function (e) {
		e.preventDefault ();
	});

}); */

</script>
</body>

</html>