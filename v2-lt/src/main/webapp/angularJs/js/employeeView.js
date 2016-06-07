var app = angular.module("empViewApp",["kendo.directives"]);


app.controller('employeeViewController', function employeeViewController($http, $scope, $location, $rootScope,$routeParams) {
	//$scope.orgId = $rootScope.employeeLoggedIn.organization.id;
	
	var employeeObj = JSON.parse(window.localStorage.getItem("employee"));
	
	$scope.employeeObj = employeeObj;
	$scope.orgId = employeeObj.organization.id;
	$scope.empId = employeeObj.id;
	
	$scope.viewOnlyEmpId = $routeParams.empId;
	
	$scope.isActive = function (viewLocation) {
		var path = $location.path().substring(0,$location.path().lastIndexOf("/")-1);
		return viewLocation.indexOf(path) > -1;
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
		
		$http.get('../ws/rest/empMgmtRestService/getEmployee/'+$scope.viewOnlyEmpId).
		success(function (data) {
			$scope.newEmployee = data.obj;
			
		});
		
	});
	
	
})