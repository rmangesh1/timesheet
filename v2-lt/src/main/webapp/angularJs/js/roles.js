var app = angular.module("rolesApp",[]);

app.controller('roleController', function roleController($http, $scope, $location) {
	
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
	
	
	$http.get('../ws/rest/empMgmtRestService/getAllRoles/'+$scope.orgId).
	success(function (data) {
		$scope.roles = data;
	});
	
	$scope.newRole = {};
	
	$scope.saveRecord = function() {
		$("#wsResponse").html('');
		if($("#role-form").valid()) {
			$http.post('../ws/rest/empMgmtRestService/save/role/'+$scope.orgId, $scope.newRole ).
			success(function(data) {
				if(data.errorResponse != null) {
					console.log(data.errorResponse);
					$("#wsResponse").html(data.errorResponse);
				}
				$http.get('../ws/rest/empMgmtRestService/getAllRoles/'+$scope.orgId).
				success(function (data) {
					$scope.roles = data;
				});
			});
			$scope.newRole = {};
		}
	}
	
	$scope.edit = function(id) {
		$("#wsResponse").html('');
		for (i in $scope.roles) {
			if($scope.roles[i].id == id) {
				$scope.newRole = angular.copy($scope.roles[i]);
			}
		}
		
	}
	
	$scope.deleteRole = function(id) {
		$("#wsResponse").html('');
		$http.post('../ws/rest/empMgmtRestService/delete/role/'+id).
		success(function(data) {
			$http.get('../ws/rest/empMgmtRestService/getAllRoles/'+$scope.orgId).
			success(function (data) {
				$scope.roles = data;
			});
		});
	}
	
	$scope.newRecord = function(id) {
		$("#wsResponse").html('');
		$scope.newRole = {};
		
	}
})