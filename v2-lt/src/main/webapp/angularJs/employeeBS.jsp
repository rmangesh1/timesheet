<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>EMS</title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">    
    
    <jsp:include page="./pagecss.jsp"></jsp:include>
    <script src="js/kendo.all.min.js"></script>
    <script src="js/employee.js"></script>

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

  </head>

<body>
<div class="holder">

<jsp:include page="./navbar.jsp"></jsp:include>

<jsp:include page="./subnavbar.jsp"></jsp:include>    
    
    
<div class="main">

    <div class="container">


      <div class="row" data-ng-app="empApp" data-ng-controller="employeeController">
      	
      	<div class="col-md-12">
      		
      		<div class="widget stacked">

      			<div class="widget-header">
					<i class="icon-ok"></i>
					<h3>Employee</h3>
				</div> <!-- /widget-header -->
				
				<div class="widget-content">

					<h3>Employee Details</h3>
					<input type="hidden" id="orgId" value="81" />
					
					<form action="/" id="employee-form" role="form" class="form-horizontal">
						
						
						<br />

						<fieldset>
						
						<div class="col-lg-10"><h4>Employee main details</h4></div>
						<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">First Name</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control required" name="firstName" id="firstName" data-ng-model="newEmployee.firstName">
								</div>
						    </div>
						</div>

						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Last Name</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control required" name="lastName" id="lastName" data-ng-model="newEmployee.lastName">
								</div>
						    </div>
						</div>
						
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Address</label>
								<div class="col-lg-10">
							        <textarea class="form-control required" name="address" id="address" rows="4" data-ng-model="newEmployee.address"></textarea>
								</div>
						    </div>
						</div>
						
						
						<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
						
						<div class="col-lg-10"><h4>Employee contact details</h4></div>
						<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Primary Phone</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control required" name="primaryPhone" id="primaryPhone" data-ng-model="newEmployee.primaryPhone">
								</div>
						    </div>
						</div>
						
						

						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Primary Email</label>
								<div class="col-lg-10">
							        <input type="email" class="form-control required" name="primaryEmail" id="primaryEmail" data-ng-model="newEmployee.primaryEmail">
								</div>
						    </div>
						</div>
						
						<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
						<div class="col-lg-10"></div>
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Mobile</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control" name="mobile" id="mobile" data-ng-model="newEmployee.mobile">
								</div>
						    </div>
						</div>
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Website</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control" name="website" id="website" data-ng-model="newEmployee.website">
								</div>
						    </div>
						</div>
						
						<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
						<div class="col-lg-10"></div>
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Fax</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control" name="fax" id="fax" data-ng-model="newEmployee.fax">
								</div>
						    </div>
						</div>
						
						<div class="col-lg-10"><h4>Employee designation details</h4></div>
						<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Type</label>
								<div class="col-lg-10">
							        <select name="deptSelect" data-ng-options="type as type for type in empScreenDetails.types" class="form-control required" data-ng-model="newEmployee.type" >
							     		<option value="">Select..</option>
   								 	</select>
								</div>
						    </div>
						</div>
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Grade</label>
								<div class="col-lg-10">
							        <select name="deptSelect" data-ng-options="grade.id as grade.grade for grade in empScreenDetails.grades" class="form-control required" data-ng-model="newEmployee.grade.id" >
							     		<option value="">Select..</option>
   								 	</select>
								</div>
						    </div>
						</div>
						
						
						<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
						<div class="col-lg-10"></div>
						
						<!-- <div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Role</label>
								<div class="col-lg-10">
							        <select data-ng-model="newEmployee.roles" class="form-control required" data-ng-change="onMappingChange()">
							        	<option value="">Select..</option>
							        	<option data-ng-repeat="value in activityMapping.values" value="{{value}}">{{value}}</option>
							        </select>
								</div>
						    </div>
						</div> -->
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Roles</label>
								<div class="col-lg-10">
								   <div class="demo-section k-header">
								       <select kendo-multi-select k-data-source="rolesList" k-placeholder="'Select roles..'" k-data-text-field="'role'" k-data-value-field="'id'" class="form-control required" ng-model="newEmployee.selectedRoles"></select>
								       <!-- <p ng-show="selectedIds.length" style="padding-top: 1em;">Selected: {{ selectedIds }}</p> -->
								    </div>
								</div>
						    </div>
						</div>
						
					<!-- 	<div id="example">
						   <div class="demo-section k-header">
						       <h4>Select product</h4>
						       <select kendo-multi-select k-options="selectOptions" k-ng-model="selectedIds"></select>
						       <p ng-show="selectedIds.length" style="padding-top: 1em;">Selected: {{ selectedIds }}</p>
						    </div>
						</div> -->
						
						<div class="col-lg-10"><h4>Employee allocation details</h4></div>
						<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
						
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Departments</label>
								<div class="col-lg-10">
								   <div class="demo-section k-header">
								       <select kendo-multi-select k-data-source="deptList" k-placeholder="'Select departments..'" k-data-text-field="'deptName'" k-data-value-field="'deptId'" class="form-control required" ng-model="newEmployee.selectedDepartments" data-ng-change="onDeptChange()"></select>
								       <!-- <p ng-show="selectedIds.length" style="padding-top: 1em;">Selected: {{ selectedIds }}</p> -->
								    </div>
								</div>
						    </div>
						</div>
						
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Projects</label>
								<div class="col-lg-10">
								   <div class="demo-section k-header">
								       <select kendo-multi-select k-data-source="projList" k-placeholder="'Select projects..'" k-data-text-field="'projectName'" k-data-value-field="'id'" class="form-control required" ng-model="newEmployee.selectedProjects"></select>
								       <!-- <p ng-show="selectedIds.length" style="padding-top: 1em;">Selected: {{ selectedIds }}</p> -->
								    </div>
								</div>
						    </div>
						</div>
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Manager</label>
								<div class="col-lg-10">
							        <select name="managerSelect" data-ng-options="manager.managerId as manager.managerName for manager in empScreenDetails.managers" class="form-control" data-ng-model="newEmployee.managerId" >
							     		<option value="">Select..</option>
   								 	</select>
								</div>
						    </div>
						</div>
						
						<div class="col-lg-10"><h4>Employee login details</h4></div>
						<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
						
						
						<label for="name" class="col-lg-10">Username : {{newEmployee.primaryEmail}}</label>
						
						<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
												
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Password</label>
								<div class="col-lg-10">
							        <input type="password" class="form-control required" name="password" id="password" data-ng-model="newEmployee.password">
								</div>
						    </div>
						</div>
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">User Role</label>
								<div class="col-lg-10">
							        <select name="userRoleSelect" data-ng-options="userRole.id as userRole.userRole for userRole in empScreenDetails.userRoles" class="form-control required" data-ng-model="newEmployee.userRole.id" >
							     		<option value="">Select..</option>
   								 	</select>
								</div>
						    </div>
						</div>
						
						    
						    <div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
							<div class="col-lg-10"></div>
							<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
							
							<div class="col-lg-8">
						    <div class="form-group">

						    	<div class="col-lg-8">
						      <button class="btn btn-success" data-ng-click="saveRecord()"><i class="icon-ok"></i> Save or update</button>&nbsp;&nbsp;
						      <button class="btn btn-default" data-ng-click="newRecord()"><i class="icon-plus"></i>&nbsp;New/Reset</button>
						  </div>
						    </div>
							</div>
							
							<div class="col-lg-10"></div>
							<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
							
							<div class="pull-right">
								<div class="form-group">
							      <label for="name" class="col-lg-4">Search : </label>
									<div class="col-lg-8">
							        	<input type="text" class="form-control" name="searchKeyword" id="searchKeyword" data-ng-model="searchKeyword">
							 
									</div>
							    </div>
								<!-- Search : <input type="text" class="form-control required" name="searchKeyword" id="searchKeyword" data-ng-model="searchKeyword"> -->
							</div>
						</fieldset>

					</form>
						<div class="col-lg-10" id="wsResponse"></div>
						
						<!-- Employee table -->
					<div data-ng-show="employees.length">
						<div class="table-responsive">
						<table class="table table-bordered table-hover table-striped">
							<thead>
							  <tr>
								<th>First name</th>
								<th>Last name</th>
								<th>Email</th>
								<th>Phone</th>
								<th></th>
							  </tr>
							</thead>
							<tbody>
							  <tr data-ng-repeat="employee in employees | filter: searchKeyword">
								<td class="col-md-2">{{employee.firstName}}</td>
								<td class="col-md-3">{{employee.lastName}}</td>
								<td class="col-md-3">{{employee.primaryEmail}}</td>
								<td class="col-md-2">{{employee.primaryPhone}}</td>
								<td class="col-md-2">
								<button class="btn btn-info" data-ng-click="edit(employee.id)"><i class="icon-edit"></i>&nbsp;Edit</button>&nbsp;&nbsp;&nbsp;
								<button class="btn btn-warning" data-ng-click="deleteEmployee(employee.id)"><i class="icon-trash"></i>&nbsp;Delete</button>
								</td>
							  </tr>
							</tbody>
						  </table>
					  </div>
					</div>
					<div class="col-lg-10" data-ng-show="!employees.length">
						<span>No employees added yet!</span>
					</div>
						
				</div> <!-- /widget-content -->
					
			</div> <!-- /widget -->					
			
	    </div> <!-- /col-md-12 -->     	
      	
      	
      </div> <!-- /row -->

    </div> <!-- /container -->
    
</div> <!-- /main -->
    

 
<jsp:include page="./footer.jsp"></jsp:include>
    
</div>
<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="./js/jquery-ui-1.10.0.custom.min.js"></script>
<script src="./js/bootstrap.min.js"></script>

<script src="./js/jquery.validate.js"></script>

<script src="./js/Application.js"></script>
<script src="./js/validation.js"></script>

<script>

$(function () {

	// Validate the form on load
	//$('#validation-form').submit ();

	// Block the form from submitting
	$('form').submit (function (e) {
		e.preventDefault ();
	});

});

</script>

  </body>
</html>
