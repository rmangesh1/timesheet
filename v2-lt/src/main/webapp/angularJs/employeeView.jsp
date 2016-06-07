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
						
						<jsp:include page="./employeeCommon.jsp"></jsp:include> 
						
						    
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
								<button class="btn btn-info" data-ng-click="edit(employee.id)"><i class="icon-edit"></i>&nbsp;Edit</button>&nbsp;&nbsp;
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
