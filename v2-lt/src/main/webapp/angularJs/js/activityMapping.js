var app = angular.module("AMApp",[]);
var combinedFactors = "";

app.controller('activityMappingController', function activityMappingController($http, $scope, $location) {
	
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
	
	$http.get('../ws/rest/empMgmtRestService/getAllActivityMappings/'+$scope.orgId).
	success(function (data) {
		console.log(angular.toJson(data.obj));
		$scope.activityMappings = data.obj;
	});
	
	$scope.activityForms = {};
	
	$scope.onMappingChange = function() {
		var flag = true;
		combinedFactors = "";
		for (i in $scope.activityMappings) {
			if(!(typeof $scope.activityMappings[i].selectFactor === 'undefined' || $scope.activityMappings[i].selectFactor == "")) {
				combinedFactors += $scope.activityMappings[i].selectFactor+".";
			} else {
				flag = false;
				break;
			} 
		}
		if(flag) {
			combinedFactors = combinedFactors.replace(/.\s*$/, "");
			$http.post('../ws/rest/empMgmtRestService/getAllActivitiesForMapping/'+$scope.orgId, combinedFactors ).
			success(function(data) {
				$scope.activityForms = data.obj;
			});
		} else {
			$scope.activityForms = {};
		}
	}
	
	$scope.saveRecord = function() {
		$http.post('../ws/rest/empMgmtRestService/save/activitymapping/'+$scope.orgId+'/'+combinedFactors, $scope.activityForms ).
		success(function(data) {
			$("#wsResponse").html("Activities updated!");
		});
	}
	
	$scope.addMoreActivities = function() {
		$location.path('/activity');
	}
})