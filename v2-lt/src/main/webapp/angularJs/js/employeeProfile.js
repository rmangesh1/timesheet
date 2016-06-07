var app = angular.module("empProfileApp",["kendo.directives"]);


app.controller('employeeProfileController', function employeeProfileController($http, $scope, $location, $rootScope) {
	//$scope.orgId = $rootScope.employeeLoggedIn.organization.id;
	
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
	
	$scope.newEmployee = {};
	
	$scope.rolesList = new kendo.data.DataSource();
	$scope.deptList = new kendo.data.DataSource();
	$scope.projList = new kendo.data.DataSource();
	
	
	$http.get('../ws/rest/empMgmtRestService/getEmployeeScreenDetails/'+$scope.orgId).
	success(function (data) {
		$scope.empScreenDetails = data.obj;
		
		$scope.rolesList.data($scope.empScreenDetails.roles);
		$scope.deptList.data($scope.empScreenDetails.departments);
		$scope.projList.data($scope.empScreenDetails.projects);
		
		$http.get('../ws/rest/empMgmtRestService/getEmployee/'+$scope.empId).
		success(function (data) {
			$scope.newEmployee = data.obj;
			
		});
		
	});
	
	$scope.saveRecord = function() {
		$("#wsResponse").html('');
		if($("#employee-form").valid()) {
			$http.post('../ws/rest/empMgmtRestService/save/employee/'+$scope.orgId, $scope.newEmployee ).
			success(function(data) {
				if(data.errorResponse != null) {
					console.log(data.errorResponse);
					$("#wsResponse").html(data.errorResponse);
				} else {
					$("#wsResponse").html('Details Updated!');
				}
			});
		}
	}
	
	
})