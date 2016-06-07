var app = angular.module("deptApp",[]);

app.controller('departmentController', function departmentController($http, $scope, $location) {
	
	var employeeObj = JSON.parse(window.localStorage.getItem("employee"));
	
	$scope.employeeObj = employeeObj;
	$scope.orgId = employeeObj.organization.id;
	
	$scope.isActive = function (viewLocation) { 
		return viewLocation.indexOf($location.path()) > -1;
	 };
	 
	 $scope.$on('$viewContentLoaded', function() {
			Application.init ();
			initValidation();
		})

	
	$http.get('../ws/rest/empMgmtRestService/getAllDepartments/'+$scope.orgId).
	success(function (data) {
		$scope.departments = data;
	});
	
	$scope.newDepartment = {};
	
	$scope.saveRecord = function() {
		$("#wsResponse").html('');
		if($("#department-form").valid()) {
			$http.post('../ws/rest/empMgmtRestService/save/department/'+$scope.orgId, $scope.newDepartment ).
			success(function(data) {
				if(data.errorResponse != null) {
					console.log(data.errorResponse);
					$("#wsResponse").html(data.errorResponse);
				}
				$http.get('../ws/rest/empMgmtRestService/getAllDepartments/'+$scope.orgId).
				success(function (data) {
					$scope.departments = data;
				});
			});
			$scope.newDepartment = {};
		}
	}
	
	$scope.edit = function(id) {
		$("#wsResponse").html('');
		for (i in $scope.departments) {
			if($scope.departments[i].id == id) {
				$scope.newDepartment = angular.copy($scope.departments[i]);
			}
		}
		
	}
	
	$scope.deleteDepartment = function(id) {
		$("#wsResponse").html('');
		$http.post('../ws/rest/empMgmtRestService/delete/department/'+id).
		success(function(data) {
			console.log(data);
			if(data.errorResponse != null) {
				console.log(data.errorResponse);
				$("#wsResponse").html(data.errorResponse);
			} else {
				$http.get('../ws/rest/empMgmtRestService/getAllDepartments/'+$scope.orgId).
				success(function (data) {
					$scope.departments = data;
				});
			}
		});
	}
	
	$scope.newRecord = function(id) {
		$("#wsResponse").html('');
		$scope.newDepartment = {};
		
	}
})