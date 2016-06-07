<div class="holder">

<jsp:include page="./navbar.jsp"></jsp:include>

<jsp:include page="./subnavbar.jsp"></jsp:include>    
    
    
<div class="main">

    <div class="container">


      <div class="row" data-ng-app="searchApp" data-ng-controller="searchController">
      	
      	<div class="col-md-12">
      		
      		<div class="widget stacked">

      			<div class="widget-header">
					<i class="icon-ok"></i>
					<h3>Search</h3>
				</div> <!-- /widget-header -->
				
				<div class="widget-content">

					<h3>Search</h3>
					<input type="hidden" id="orgId" value="1" />
					
					<form action="" id="search-form" role="form" class="form-horizontal">


						<br />

						<fieldset>
						<div class="col-lg-4">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Search</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control required" name="value" id="value" data-ng-model="searchForm.value">
								</div>
						    </div>
						</div>
						
						<div class="col-lg-4">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Search for</label>
								<div class="col-lg-10">
							        <!-- <select name="searchSelect" data-ng-options="searchFactor for searchFactor in searchForm.criterias" class="form-control required" data-ng-model="searchForm.searchFor" >
							     	<option value="">Select..</option>
   								 </select> -->
   								 <select data-ng-model="searchForm.searchFor" class="form-control required" >
							        	<option value="">Select..</option>
							        	<option data-ng-repeat="searchFactor in searchForm.criterias" value="{{searchFactor}}">{{searchFactor}}</option>
							        </select>
								</div>
						    </div>
						</div>

							<br /><br/><br/><br/><br/><br/>
				          
							<div class="col-lg-8">
						    <div class="form-group">

						    	<div class="col-lg-8">
						      <button class="btn btn-default" data-ng-click="findRecord()"><i class="icon-search"></i> Search</button>&nbsp;&nbsp;
						  </div>
						    </div>
							</div>
						  </fieldset>
						</form>
						<div class="col-lg-10" id="wsResponse"></div>
						<br/><br/>
						
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
							  <tr data-ng-repeat="employee in employees">
								<td class="col-md-3">{{employee.firstName}}</td>
								<td class="col-md-3">{{employee.lastName}}</td>
								<td class="col-md-3">{{employee.primaryEmail}}</td>
								<td class="col-md-2">{{employee.primaryPhone}}</td>
								<td class="col-md-1">
									<!-- <button class="btn btn-info" data-ng-click="viewEmployee(employee.id)"><i class="icon-eye-open"></i>&nbsp;View</button>&nbsp;&nbsp;&nbsp; -->
									<a class="btn btn-info" href="#viewEmployee/{{employee.id}}"><i class="icon-edit"></i>&nbsp;View</a>&nbsp;&nbsp;&nbsp;
								</td>
							  </tr>
							</tbody>
						  </table>
					  </div>
					</div>
					<!-- Project table -->
					<div data-ng-show="projects.length">
						<div class="table-responsive">
						<table class="table table-bordered table-hover table-striped">
							<thead>
							  <tr>
								<th>Department</th>
								<th>Project Name</th>
								<th>Project Code</th>
								<th>Project Location</th>
								<th></th>
							  </tr>
							</thead>
							<tbody>
							  <tr data-ng-repeat="project in projects">
								<td class="col-md-3">{{project.department.name}}</td>
								<td class="col-md-3">{{project.projectName}}</td>
								<td class="col-md-3">{{project.projectCode}}</td>
								<td class="col-md-2">{{project.location}}</td>
								<td class="col-md-1">
									<button class="btn btn-info" data-ng-click="viewProject(project.id)"><i class="icon-eye-open"></i>&nbsp;View</button>&nbsp;&nbsp;&nbsp;
								</td>
							  </tr>
							</tbody>
						  </table>
					  </div>
					</div>
					<!-- Project table -->
					<div data-ng-show="departments.length">
						<div class="table-responsive">
						<table class="table table-bordered table-hover table-striped">
							<thead>
							  <tr>
								<th>Department</th>
								<th>Department Description</th>
								<th></th>
							  </tr>
							</thead>
							<tbody>
							  <tr data-ng-repeat="department in departments">
								<td class="col-md-5">{{department.name}}</td>
								<td class="col-md-5">{{department.departmentDescription}}</td>
								<td class="col-md-2">
									<button class="btn btn-info" data-ng-click="viewDepartment(project.id)"><i class="icon-eye-open"></i>&nbsp;View</button>&nbsp;&nbsp;&nbsp;
								</td>
							  </tr>
							</tbody>
						  </table>
					  </div>
					</div>
					<div class="col-lg-10" data-ng-show="!(employees.length || projects.length || departments.length) && searched">
						<span>No results!</span>
					</div>
				</div> <!-- /widget-content -->
					
			</div> <!-- /widget -->					
			
	    </div> <!-- /col-md-12 -->     	
      	
      	
      </div> <!-- /row -->

    </div> <!-- /container -->
    
</div> <!-- /main -->
    

 
<jsp:include page="./footer.jsp"></jsp:include>
    
</div>
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