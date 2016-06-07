var app = angular.module("loginApp",[]);

app.controller('LoginController', function LoginController($http, $scope, $window, $location, $rootScope) {
	
	$scope.loginData = {};
	
	window.localStorage.removeItem("employee");
	
	$scope.loginEms = function() {
		
		$http.post('../ws/rest/empMgmtRestService/login', angular.toJson($scope.loginData)).
		success(function (data) {
			$("#wsResponse").html('');
			if(data.errorResponse != null) {
				console.log(data.errorResponse);
				$("#wsResponse").html(data.errorResponse);
			} else {
				
				window.localStorage.setItem("employee", JSON.stringify(data.obj));
				$rootScope.employeeLoggedIn = data.obj;
				
				$location.path('/search');
			}
		});
		
		$scope.loginData = {};
	}
})

