var app = angular.module("orgApp",[]);

app.controller('organizationController', function organizationController($http, $scope, $location) {
	
	var employeeObj = JSON.parse(window.localStorage.getItem("employee"));
	
	$scope.employeeObj = employeeObj;
	$scope.orgId = employeeObj.organization.id;
	
	$scope.isActive = function (viewLocation) { 
		return viewLocation.indexOf($location.path()) > -1;
	 };
	 
	 $scope.$on('$viewContentLoaded', function() {
			Application.init ();
			initValidation();
		});

	
	$http.get('../ws/rest/empMgmtRestService/getOrgDetails/'+$scope.orgId).
	success(function (data) {
		$scope.organization = data.obj;
	});
	
	
	$scope.saveRecord = function() {
		$("#wsResponse").html('');
		if($("#organization-form").valid()) {
			$http.post('../ws/rest/empMgmtRestService/save/organization', $scope.organization ).
			success(function(data) {
				if(data.errorResponse != null) {
					$("#wsResponse").html(data.errorResponse);
					
				} else {
					$http.get('../ws/rest/empMgmtRestService/getOrgDetails/'+$scope.orgId).
					success(function (data) {
						$scope.organization = data.obj;
					});
					$("#wsResponse").html("Organization updated!");
					location.path("/organization");
				}
			});
		}
	}
})