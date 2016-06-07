var app = angular.module("viewTimesheetApp",[]);

app.controller('viewTimesheetController', function viewTimesheetController($http, $scope,$routeParams, $window, $location, $rootScope) {

	$scope.viewEmpId = $routeParams.empId;
	
	var employeeObj = JSON.parse(window.localStorage.getItem("employee"));
	
	$scope.employeeObj = employeeObj;
	$scope.orgId = employeeObj.organization.id;
	
	$scope.empId = employeeObj.id;
	
	$scope.isActive = function (viewLocation) {
		var path = $location.path().substring(0,$location.path().lastIndexOf("/")-1);
		return viewLocation.indexOf(path) > -1;
	 };
	
	$scope.$on('$viewContentLoaded', function() {
		Application.init ();
		initValidation();
	})
	
	$scope.tsDetailsForm = {};
	
	var d = new Date();
	function getDateInFormat(d) {
		var day = "";
		var month = "";
		var year = "";
		var monthInt = d.getMonth() + 1;
		if(d.getDate() < 10) {
			day = "0" + d.getDate();
		} else {
			day = d.getDate();
		}
		if(monthInt < 10) {
			month = "0" + monthInt;
		} else {
			month = monthInt;
		}
		year = d.getFullYear();
		var formattedDate = day+"."+month+"."+year;
		return formattedDate;
	}
	
	$scope.tsDetailsForm.dayOfWeek = getDateInFormat(d);
	
	
	$http.get('../ws/rest/empMgmtRestService/getAllEmpTimeSheetFactors/'+$scope.viewEmpId).
	success(function (data) {
		$scope.timeSheetScreen = data.obj;
	});
	
	$http.get('../ws/rest/empMgmtRestService/getEmpTimeSheet/'+$scope.viewEmpId+'/'+$scope.tsDetailsForm.dayOfWeek).
	success(function (data) {
		$scope.tsDetailsForm = data.obj;
	});
	
	$scope.findRecord = function() {
		$scope.tsDetailsForm.dayOfWeek = $("#tsDate").val();
		$http.get('../ws/rest/empMgmtRestService/getEmpTimeSheet/'+$scope.viewEmpId+'/'+$scope.tsDetailsForm.dayOfWeek).
		success(function (data) {
			$scope.tsDetailsForm = data.obj;
		});
	}
})

