var app = angular.module("projApp",[]);

app.controller('projectController', function($http, $scope, $location) {
	
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
	
	$http.get('../ws/rest/empMgmtRestService/getAllProjects/'+$scope.orgId).
	success(function (data) {
		$scope.projects = data.obj;
	});
	
	 $http.get('../ws/rest/empMgmtRestService/getAllDepartments/'+$scope.orgId).
     success(function(data) {
         $scope.departments = data;
     });
	
	$scope.newProject = {};
	
	$scope.saveRecord = function() {
		$("#wsResponse").html('');
		if($("#project-form").valid()) {
			$http.post('../ws/rest/empMgmtRestService/save/project', $scope.newProject ).
			success(function(data) {
				if(data.errorResponse != null) {
					console.log(data.errorResponse);
					$("#wsResponse").html(data.errorResponse);
				}
				

			  $http.get('../ws/rest/empMgmtRestService/getAllDepartments/'+$scope.orgId).
			        success(function(data) {
			            $scope.departments = data;
			  });
				  
				  
				$http.get('../ws/rest/empMgmtRestService/getAllProjects/'+$scope.orgId).
				success(function (data) {
					$scope.projects = data.obj;
				});
			});
			$scope.newProject = {};
		}
	}
	
	$scope.edit = function(id) {
		$("#wsResponse").html('');
		for (i in $scope.projects) {
			if($scope.projects[i].id == id) {
				console.log($scope.projects[i]);
				$scope.newProject = angular.copy($scope.projects[i]);
			}
		}
		
	}
	
	$scope.deleteProject = function(id) {
		$("#wsResponse").html('');
		$http.post('../ws/rest/empMgmtRestService/delete/project/'+id).
		success(function(data) {
			if(data.errorResponse != null) {
				console.log(data.errorResponse);
				$("#wsResponse").html(data.errorResponse);
			} else {
				$http.get('../ws/rest/empMgmtRestService/getAllProjects/'+$scope.orgId).
				success(function (data) {
					$scope.projects = data.obj;
				});
			}
		});
	}
	
	$scope.newRecord = function(id) {
		$("#wsResponse").html('');
		$scope.newProject = {};
		
	}
})