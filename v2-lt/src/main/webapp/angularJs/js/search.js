var app = angular.module("searchApp",[]);

app.controller('searchController', function searchController($http, $scope, $window, $location) {
	
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


	
	$scope.employees = {};
	$scope.projects = {};
	$scope.departments = {};
	$scope.searched = false;
	
	$scope.searchForm = {
			
	"value" : "",
	"searchFor" : "",
	"criterias" : ["Employee",
	       		"Department","Project"]
	};
	
	$scope.findRecord = function() {
		$scope.searched = false;
		$("#wsResponse").html('');
		if($("#search-form").valid()) {
			$http.post('../ws/rest/empMgmtRestService/findSearch/'+$scope.orgId, $scope.searchForm ).
			success(function(data) {
				$scope.employees = {};
				$scope.projects = {};
				$scope.departments = {};
				$scope.searched = true;
				if(data.errorResponse != null) {
					console.log(data.errorResponse);
					$("#wsResponse").html(data.errorResponse);
				} else {
					if($scope.searchForm.searchFor == 'Employee') {
						$scope.employees = data.obj.searchResult;
					}
					else if($scope.searchForm.searchFor == 'Project') {
						$scope.projects = data.obj.searchResult;
					}
					else if($scope.searchForm.searchFor == 'Department') {
						$scope.departments = data.obj.searchResult;
					}
					console.log(data.obj.searchResult);
				}
			});
		}
	}
	
	$scope.view = function() {
		//$window.location.href = 'employeeBS.jsp';
	}
})