var app = angular.module("empApp",["kendo.directives"]);


app.controller('employeeController', function employeeController($http, $scope, $location, $rootScope) {
	//$scope.orgId = $rootScope.employeeLoggedIn.organization.id;
	
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
	
	/* $scope.load = function() {
		 Application.init ();
		 var rules = {
			    	rules: {
						name: {
							minlength: 2,
							required: true
						},
						email: {
							required: true,
							email: true
						},
						subject: {
							minlength: 2,
							required: true
						},
						message: {
							minlength: 2,
							required: true
						},
				      validateSelect: {
				      	required: true
			      	},
				      validateCheckbox: {
				      	required: true,
				      	minlength: 2	
			      	  },
				      validateRadio: {
				      	required: true
				      }
					}
			    };
			
		    var validationObj = $.extend (rules, Application.validationRules);
		    
			$('#activity-form').validate(validationObj);
			$('#grade-form').validate(validationObj);
			$('#role-form').validate(validationObj);
			$('#department-form').validate(validationObj);
			$('#activityMapping-form').validate(validationObj);
			$("#organization-form").validate(validationObj);
			$("#project-form").validate(validationObj);
			$("#employee-form").validate(validationObj);
			$("#search-form").validate(validationObj);
	   };

	   //don't forget to call the load function
	   $scope.load();*/
	
	
	$scope.newEmployee = {};
	
	$http.get('../ws/rest/empMgmtRestService/getAllEmployees/'+$scope.orgId).
	success(function (data) {
		$scope.employees = data;
	});
	
	$scope.rolesList = new kendo.data.DataSource();
	$scope.deptList = new kendo.data.DataSource();
	$scope.projList = new kendo.data.DataSource();
	
	
	$http.get('../ws/rest/empMgmtRestService/getEmployeeScreenDetails/'+$scope.orgId).
	success(function (data) {
		$scope.empScreenDetails = data.obj;
		
		$scope.rolesList.data($scope.empScreenDetails.roles);
		$scope.deptList.data($scope.empScreenDetails.departments);
		$scope.projList.data($scope.empScreenDetails.projects);
		
	});
	
	
	
	
	$scope.edit = function(id) {
		$("#wsResponse").html('');
		$scope.projList.data($scope.empScreenDetails.projects);
		for (i in $scope.employees) {
			if($scope.employees[i].id == id) {
				$scope.newEmployee = angular.copy($scope.employees[i]);
			}
		}
		$("#employee-form").validate().resetForm();
	}
	
	$scope.deleteEmployee = function(id) {
		$("#wsResponse").html('');
		$http.post('../ws/rest/empMgmtRestService/delete/employee/'+id).
		success(function(data) {
			$http.get('../ws/rest/empMgmtRestService/getAllEmployees/'+$scope.orgId).
			success(function (data) {
				$scope.employees = data;
			});
		});
		$scope.newEmployee = {};
	}
	
	$scope.newRecord = function(id) {
		$("#wsResponse").html('');
		$scope.newEmployee = {};
		
	}
	
	$scope.onDeptChange = function () {
		var projects = [];
		
		for(j in $scope.empScreenDetails.departments) {
			for(i in $scope.newEmployee.selectedDepartments) {
				if($scope.newEmployee.selectedDepartments[i] == $scope.empScreenDetails.departments[j].deptId) {
					for(x in $scope.empScreenDetails.departments[j].projects) {
						projects.push($scope.empScreenDetails.departments[j].projects[x]);
					}
				}
			}
		}
		if($scope.newEmployee.selectedDepartments != null && $scope.newEmployee.selectedDepartments.length > 0) {
			$scope.projList.data(projects);
		}
	}
	
	$scope.saveRecord = function() {
		$("#wsResponse").html('');
		if($("#employee-form").valid()) {
			$http.post('../ws/rest/empMgmtRestService/save/employee/'+$scope.orgId, $scope.newEmployee ).
			success(function(data) {
				if(data.errorResponse != null) {
					console.log(data.errorResponse);
					$("#wsResponse").html(data.errorResponse);
				}
				$http.get('../ws/rest/empMgmtRestService/getAllEmployees/'+$scope.orgId).
				success(function (data) {
					$scope.employees = data;
				});
				$http.get('../ws/rest/empMgmtRestService/getEmployeeScreenDetails/'+$scope.orgId).
				success(function (data) {
					$scope.empScreenDetails = data.obj;
					
					$scope.rolesList.data($scope.empScreenDetails.roles);
					$scope.deptList.data($scope.empScreenDetails.departments);
					$scope.projList.data($scope.empScreenDetails.projects);
					
				});
			});
			$scope.newEmployee = {};
		}
	}
	
	
})