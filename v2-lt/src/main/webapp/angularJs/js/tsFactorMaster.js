var app = angular.module("TSFacMasApp",[]);

app.controller('tsFactorMasterController',function tsFactorMasterController($http, $scope, $location) {
	
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
	
	$http.get('../ws/rest/empMgmtRestService/getTSFactorMaster/'+$scope.orgId).
	success(function (data) {
		$scope.tsFactorForms = data.obj;
	});
	
	//$scope.activityForms = {};
	
	$scope.saveRecord = function() {
		$http.post('../ws/rest/empMgmtRestService/save/tsfactors/'+$scope.orgId, $scope.tsFactorForms ).
		success(function(data) {
			$("#wsResponse").html("Factors updated!");
		});
	}
	
})