<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>EMS</title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">    
    
    <jsp:include page="./pagecss.jsp"></jsp:include>
    <script src="js/project.js"></script>

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


      <div class="row" data-ng-app="projApp" data-ng-controller="projectController">
      	
      	<div class="col-md-12">
      		
      		<div class="widget stacked">

      			<div class="widget-header">
					<i class="icon-ok"></i>
					<h3>project</h3>
				</div> <!-- /widget-header -->
				
				<div class="widget-content">

					<h3>Project Primary Details</h3>
					<input type="hidden" id="orgId" value="1" />
					
					<form action="" id="project-form" role="form" class="form-horizontal">


						<br />

						<fieldset>
						
						<div class="col-lg-4">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Department Name</label>
								<div class="col-lg-10">
							     <select name="deptSelect" data-ng-options="dept.id as dept.name for dept in departments" class="form-control required" data-ng-model="newProject.department.id" >
							     	<option value="">Select..</option>
   								 </select>
								</div>
						    </div>
						</div>
						
						<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
						<div class="col-lg-10"></div>
						
						<div class="col-lg-4">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Project Name</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control required" name="projectName" id="projectName" data-ng-model="newProject.projectName">
								</div>
						    </div>
						</div>

						<div class="col-lg-4">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Project Code</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control required" name="projectCode" id="projectCode" data-ng-model="newProject.projectCode">
								</div>
						    </div>
						</div>
						    
						    
					    <div class="col-lg-4">
					    <div class="form-group">
						    <label for="name" class="col-lg-10">Project Location</label>
							<div class="col-lg-10">
						        <input type="text" class="form-control" name="location" id="location" data-ng-model="newProject.location">
							</div>
					    </div>
						</div>
							
							<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
							<div class="col-lg-10"></div>
						
				          
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
						
					<!-- project table -->
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
							    <td class="col-md-2">{{project.department.name}}</td>
								<td class="col-md-3">{{project.projectName}}</td>
								<td class="col-md-2">{{project.projectCode}}</td>
								<td class="col-md-3">{{project.location}}</td>
								<td class="col-md-2">
								<button class="btn btn-info" data-ng-click="edit(project.id)"><i class="icon-edit"></i>&nbsp;Edit</button>&nbsp;&nbsp;&nbsp;
								<button class="btn btn-warning" data-ng-click="deleteProject(project.id)"><i class="icon-trash"></i>&nbsp;Delete</button>
								</td>
							  </tr>
							</tbody>
						  </table>
					  </div>
					</div>
					<div class="col-lg-10" data-ng-show="!projects.length">
						<span>No projects added yet!</span>
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
<script src="./js/jquery-1.9.1.min.js"></script>
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
