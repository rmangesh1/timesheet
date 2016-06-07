var app = angular.module("managerApp",[]);

app.controller('managerController', function managerController($http, $scope, $location) {
	
	var employeeObj = JSON.parse(window.localStorage.getItem("employee"));
	
	$scope.employeeObj = employeeObj;
	$scope.orgId = employeeObj.organization.id;
	$scope.empId = employeeObj.id;
	
	$scope.isActive = function (viewLocation) { 
		return viewLocation.indexOf($location.path()) > -1;
	 };
	
	$scope.$on('$viewContentLoaded', function() {
		Application.init ();
		initValidation();
	})
	
	
	$http.get('../ws/rest/empMgmtRestService/fetchSubordinates/'+$scope.empId).
	success(function (data) {
		$scope.employees = data.obj;
	});
	

})