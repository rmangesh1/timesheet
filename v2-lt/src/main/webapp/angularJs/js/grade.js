var app = angular.module("gradeApp",[]);

app.controller('gradeController', function gradeController($http, $scope, $location) {
	
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
	
	
	$http.get('../ws/rest/empMgmtRestService/getAllGrades/'+$scope.orgId).
	success(function (data) {
		$scope.grades = data;
	});
	
	$scope.newGrade = {};
	
	$scope.saveRecord = function() {
		$("#wsResponse").html('');
		if($("#grade-form").valid()) {
			$http.post('../ws/rest/empMgmtRestService/save/grade/'+$scope.orgId, $scope.newGrade ).
			success(function(data) {
				if(data.errorResponse != null) {
					console.log(data.errorResponse);
					$("#wsResponse").html(data.errorResponse);
				}
				$http.get('../ws/rest/empMgmtRestService/getAllGrades/'+$scope.orgId).
				success(function (data) {
					$scope.grades = data;
				});
			});
			$scope.newGrade = {};
		}
	}
	
	$scope.edit = function(id) {
		$("#wsResponse").html('');
		for (i in $scope.grades) {
			if($scope.grades[i].id == id) {
				$scope.newGrade = angular.copy($scope.grades[i]);
			}
		}
		
	}
	
	$scope.deleteGrade = function(id) {
		$("#wsResponse").html('');
		$http.post('../ws/rest/empMgmtRestService/delete/grade/'+id).
		success(function(data) {
			$http.get('../ws/rest/empMgmtRestService/getAllGrades/'+$scope.orgId).
			success(function (data) {
				$scope.grades = data;
			});
		});
	}
	
	$scope.newRecord = function(id) {
		$("#wsResponse").html('');
		$scope.newGrade = {};
		
	}
})