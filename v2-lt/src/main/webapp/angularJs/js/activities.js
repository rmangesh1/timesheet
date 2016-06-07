var app = angular.module("actApp",[]);

app.controller('activityController', function activityController($http, $scope,$location) {
	
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
	
	$http.get('../ws/rest/empMgmtRestService/getAllActivities/'+$scope.orgId).
	success(function (data) {
		$scope.activities = data;
	});
	
	$scope.newActivity = {};
	
	$scope.saveRecord = function() {
		$("#wsResponse").html('');
		if($("#activity-form").valid()) {
			$http.post('../ws/rest/empMgmtRestService/save/activity/'+$scope.orgId, $scope.newActivity ).
			success(function(data) {
				if(data.errorResponse != null) {
					console.log(data.errorResponse);
					$("#wsResponse").html(data.errorResponse);
				}
				$http.get('../ws/rest/empMgmtRestService/getAllActivities/'+$scope.orgId).
				success(function (data) {
					$scope.activities = data;
				});
			});
			$scope.newActivity = {};
		}
	}
	
	$scope.edit = function(id) {
		$("#wsResponse").html('');
		for (i in $scope.activities) {
			if($scope.activities[i].id == id) {
				$scope.newActivity = angular.copy($scope.activities[i]);
			}
		}
		
	}
	
	$scope.deleteActivity = function(id) {
		$("#wsResponse").html('');
		$http.post('../ws/rest/empMgmtRestService/delete/activity/'+id).
		success(function(data) {
			$http.get('../ws/rest/empMgmtRestService/getAllActivities/'+$scope.orgId).
			success(function (data) {
				$scope.activities = data;
			});
		});
	}
	
	$scope.newRecord = function(id) {
		$("#wsResponse").html('');
		$scope.newActivity = {};
		
	}
})