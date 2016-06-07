<div class="holder">

<jsp:include page="./navbar.jsp"></jsp:include>

<jsp:include page="./subnavbar.jsp"></jsp:include>    
    
    
<div class="main">

    <div class="container">


      <div class="row" data-ng-app="deptApp" data-ng-controller="departmentController">
      	
      	<div class="col-md-12">
      		
      		<div class="widget stacked">

      			<div class="widget-header">
					<i class="icon-ok"></i>
					<h3>Department</h3>
				</div> <!-- /widget-header -->
				
				<div class="widget-content">

					<h3>Department Primary Details</h3>
					<input type="hidden" id="orgId" value="81" />
					
					<form action="" id="department-form" role="form" class="form-horizontal">


						<br />

						<fieldset>
						<div class="col-lg-4">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Department</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control required" name="departmentName" id="departmentName" data-ng-model="newDepartment.name">
								</div>
						    </div>
						</div>

					    <div class="col-lg-4">
					    <div class="form-group">
						    <label for="name" class="col-lg-10">Department Description</label>
							<div class="col-lg-10">
						        <input type="text" class="form-control" name="departmentDescription" id="departmentDescription" data-ng-model="newDepartment.departmentDescription">
							</div>
					    </div>
						</div>
							<br /><br/><br/><br/><br/><br/>
				          
							<div class="col-lg-8">
						    <div class="form-group">

						    	<div class="col-lg-8">
						      <button class="btn btn-success" data-ng-click="saveRecord()"><i class="icon-ok"></i> Save or update</button>&nbsp;&nbsp;
						      <button class="btn btn-default" data-ng-click="newRecord()"><i class="icon-plus"></i>&nbsp;New/Reset</button>
						  </div>
						    </div>
							</div>
						  </fieldset>
						</form>
						<div class="col-lg-10" id="wsResponse"></div>
						<br/><br/>
						
					<!-- Activity table -->
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
								<td class="col-md-4">{{department.name}}</td>
								<td class="col-md-5">{{department.departmentDescription}}</td>
								<td class="col-md-3">
								<button class="btn btn-info" data-ng-click="edit(department.id)"><i class="icon-edit"></i>&nbsp;Edit</button>&nbsp;&nbsp;&nbsp;
								<button class="btn btn-warning" data-ng-click="deleteDepartment(department.id)"><i class="icon-trash"></i>&nbsp;Delete</button>
								</td>
							  </tr>
							</tbody>
						  </table>
					  </div>
					</div>
					<div class="col-lg-10" data-ng-show="!departments.length">
						<span>No department added yet!</span>
					</div>
				</div> <!-- /widget-content -->
					
			</div> <!-- /widget -->					
			
	    </div> <!-- /col-md-12 -->     	
      	
      	
      </div> <!-- /row -->

    </div> <!-- /container -->
    
</div> <!-- /main -->
    

 
<jsp:include page="./footer.jsp"></jsp:include>
    
</div>