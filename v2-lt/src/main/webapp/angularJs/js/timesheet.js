var app = angular.module("timeSheetApp",[]);


app.controller('timeSheetController', function timeSheetController($http, $scope, $location, $rootScope) {
	
	var employeeObj = JSON.parse(window.localStorage.getItem("employee"));
	
	$scope.employeeObj = employeeObj;
	$scope.orgId = employeeObj.organization.id;
	
	$scope.empId = employeeObj.id;
	
	$scope.isActive = function (viewLocation) { 
		return viewLocation.indexOf($location.path()) > -1;
	 };
	
	$scope.$on('$viewContentLoaded', function() {
		Application.init ();
		initValidation();
	})
	
	$scope.tsDetailsForm = {};
	
	var d = new Date();
	function getDateInFormat(d) {
		var day = "";
		var month = "";
		var year = "";
		var monthInt = d.getMonth() + 1;
		if(d.getDate() < 10) {
			day = "0" + d.getDate();
		} else {
			day = d.getDate();
		}
		if(monthInt < 10) {
			month = "0" + monthInt;
		} else {
			month = monthInt;
		}
		year = d.getFullYear();
		var formattedDate = day+"."+month+"."+year;
		return formattedDate;
	}
	
	$scope.tsDetailsForm.dayOfWeek = getDateInFormat(d);
	
	$scope.date = "";
	
	$http.get('../ws/rest/empMgmtRestService/getAllEmpTimeSheetFactors/'+$scope.empId).
	success(function (data) {
		$scope.timeSheetScreen = data.obj;
	});
	
	$http.get('../ws/rest/empMgmtRestService/getEmpTimeSheet/'+$scope.empId+'/'+$scope.tsDetailsForm.dayOfWeek).
	success(function (data) {
		$scope.tsDetailsForm = data.obj;
	});
	
	
	$scope.findRecord = function() {
		$scope.tsDetailsForm.dayOfWeek = $("#tsDate").val();
		$http.get('../ws/rest/empMgmtRestService/getEmpTimeSheet/'+$scope.empId+'/'+$scope.tsDetailsForm.dayOfWeek).
		success(function (data) {
			$scope.tsDetailsForm = data.obj;
		});
	}
	
	$scope.onMappingChange = function(index) {
		var flag = true;
		$scope.activities = {};
		combinedFactors = "";
		for (i in $scope.tsDetailsForm.tsRecordForm[index].selectFactor) {
			if(!(typeof $scope.tsDetailsForm.tsRecordForm[index].selectFactor[i] === 'undefined' || $scope.tsDetailsForm.tsRecordForm[index].selectFactor[i] == "")) {
				combinedFactors += $scope.tsDetailsForm.tsRecordForm[index].selectFactor[i]+".";
			}
		}
		combinedFactors = combinedFactors.replace(/.\s*$/, "");
		var factors = combinedFactors.split(".");
		if(factors.length == $scope.timeSheetScreen.multipleMultiplicityCount) {
		//combinedFactors = $scope.orgId +"."+ combinedFactors;
		combinedFactors = "";
		var j = 0;
		for(i in $scope.timeSheetScreen.timeSheetForm) {
			if($scope.timeSheetScreen.timeSheetForm[i].tsFactor.empToempTSFactorMultiplicity == 'single') {
				combinedFactors = combinedFactors + $scope.timeSheetScreen.timeSheetForm[i].values[0]+".";
			} else {
				combinedFactors = combinedFactors + factors[j]+".";
				j++;
			}
		}
		
		combinedFactors = combinedFactors.replace(/.\s*$/, "");
		combinedFactors = $scope.orgId +"."+ combinedFactors;
		console.log(combinedFactors);
		for(i in $scope.timeSheetScreen.activityMappings) {
			if(combinedFactors === $scope.timeSheetScreen.activityMappings[i].conditionForActivities) {
				$scope.tsDetailsForm.tsRecordForm[index].activities = $scope.timeSheetScreen.activityMappings[i].activities;
				$scope.tsDetailsForm.tsRecordForm[index].conditionForActivitiesForRecord = combinedFactors;
			}
		}
		}
	}
	
	$scope.addEntry = function() {
		$http.get('../ws/rest/empMgmtRestService/getTimeSheetBlankRecord').
		success(function (data) {
			$scope.newTsRecord = data.obj;
			$scope.tsDetailsForm.tsRecordForm.push($scope.newTsRecord);
		});
	}
	
	$scope.deleteEntry = function(index) {
		if(index > 0) {
			$scope.tsDetailsForm.tsRecordForm.splice(index,1);
		}
	}
	
	$scope.saveRecord = function() {
		console.log($scope.tsDetailsForm);
		
		$("#wsResponse").html('');
		
		$scope.tsDetailsForm.dayOfWeek = $("#tsDate").val();
		
		console.log($scope.tsDetailsForm.dayOfWeek);
		$http.post('../ws/rest/empMgmtRestService/delete/timesheet/'+$scope.empId, $scope.tsDetailsForm ).
		success(function(data) {
			$http.post('../ws/rest/empMgmtRestService/save/timesheet/'+$scope.empId, $scope.tsDetailsForm ).
			success(function(data) {
				if(data.errorResponse != null) {
					console.log(data.errorResponse);
					$("#wsResponse").html(data.errorResponse);
				} else {
					$("#wsResponse").html("Time sheet saved/updated!");
				}
			});
		});
		/*$http.post('../ws/rest/empMgmtRestService/save/timesheet/'+$scope.empId, $scope.tsDetailsForm ).
		success(function(data) {
			if(data.errorResponse != null) {
				console.log(data.errorResponse);
				$("#wsResponse").html(data.errorResponse);
			} else {
				$("#wsResponse").html("Time sheet saved/updated!");
			}
		});*/
	}
	
})