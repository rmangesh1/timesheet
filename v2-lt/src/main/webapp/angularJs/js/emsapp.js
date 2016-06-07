var app = angular.module('emsApp',['ngRoute','loginApp','empApp','gradeApp','searchApp','orgApp','deptApp','projApp','actApp','AMApp','TSFacMasApp','rolesApp','managerApp','viewTimesheetApp','timeSheetApp','empProfileApp','empViewApp']);

app.config(['$routeProvider',
                  function($routeProvider) {
                    $routeProvider.
                      when('/employee', {
                        templateUrl: 'employeeView.jsp',
                        controller: 'employeeController'
                    }).
                      when('/grades', {
                        templateUrl: 'gradeView.jsp',
                        controller: 'gradeController'
                    }).
                      when('/login', {
                        templateUrl: 'loginView.jsp',
                        controller: 'LoginController'
                      }).
                      when('/search', {
                          templateUrl: 'searchView.jsp',
                          controller: 'searchController'
                        }).
                      when('/organization', {
                        templateUrl: 'organizationView.jsp',
                        controller: 'organizationController'
                        }).
                      when('/department', {
                            templateUrl: 'departmentView.jsp',
                            controller: 'departmentController'
                      }).
                      when('/project', {
                          templateUrl: 'projectView.jsp',
                          controller: 'projectController'
                        }).
                      when('/activity', {
                        templateUrl: 'activityView.jsp',
                        controller: 'activityController'
                        }).
                      when('/activityMapping', {
                            templateUrl: 'activityMappingView.jsp',
                            controller: 'activityMappingController'
                      }). 
                      when('/tsFactor', {
                          templateUrl: 'tsFactorMasterView.jsp',
                          controller: 'tsFactorMasterController'
                      }). 
                      when('/role', {
                          templateUrl: 'roleView.jsp',
                          controller: 'roleController'
                      }). 
                      when('/viewSubordinates', {
                          templateUrl: './subordinates.jsp',
                          controller: 'managerController'
                      }). 
                      when('/viewTimesheets/:empId', {
                          templateUrl: 'viewTimesheet.jsp',
                          controller: 'viewTimesheetController'
                      }).
                      when('/fillTimesheet', {
                          templateUrl: 'timesheetView.jsp',
                          controller: 'timeSheetController'
                      }).
                      when('/profile', {
                          templateUrl: 'employeeProfileView.jsp',
                          controller: 'employeeProfileController'
                      }).
                      when('/viewEmployee/:empId', {
                          templateUrl: 'employeeViewOnly.jsp',
                          controller: 'employeeViewController'
                      }).
                      otherwise({
                        redirectTo: '/login'
                      });
                }]);


/*app.directive('loadEMSValidate', function() {
    return {
        restrict: 'EA', //E = element, A = attribute, C = class, M = comment         
        templateUrl: 'employeeView.jsp',
        link: function($scope, element, attrs) {
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
        }
    }
});*/