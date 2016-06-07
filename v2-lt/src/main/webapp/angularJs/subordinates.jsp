
<div class="holder">

<jsp:include page="./navbar.jsp"></jsp:include>

<jsp:include page="./subnavbar.jsp"></jsp:include>    
    
    
<div class="main">

    <div class="container">


      <div class="row" data-ng-app="managerApp" data-ng-controller="managerController">
      	
      	<div class="col-md-12">
      		
      		<div class="widget stacked">

      			<div class="widget-header">
					<i class="icon-ok"></i>
					<h3>Subordinates</h3>
				</div> <!-- /widget-header -->
				
				<div class="widget-content">

					<h3>Subordinates Details</h3>
					<input type="hidden" id="orgId" value="1" />
					<input type="hidden" id="empId" value="7" />
					
						<div class="col-lg-10" id="wsResponse"></div>
						<br/><br/>
						
					<!-- project table -->
					<div data-ng-show="employees.length">
						<div class="table-responsive">
						<table class="table table-bordered table-hover table-striped">
							<thead>
							  <tr>
							  	<th>First name</th>
								<th>Last Name</th>
								<th>Primary Phone</th>
								<th>Primary Email</th>
								<th></th>
							  </tr>
							</thead>
							<tbody>
							  <tr data-ng-repeat="emp in employees">
							    <td class="col-md-2">{{emp.firstName}}</td>
								<td class="col-md-3">{{emp.lastName}}</td>
								<td class="col-md-2">{{emp.primaryPhone}}</td>
								<td class="col-md-3">{{emp.primaryEmail}}</td>
							
								<td class="col-md-2">
								<a class="btn btn-info" href="#viewTimesheets/{{emp.id}}"><i class="icon-edit"></i>&nbsp;View timesheet</a>&nbsp;&nbsp;&nbsp;
						
								</td>
							  </tr>
							</tbody>
						  </table>
					  </div>
					</div>
					<div class="col-lg-10" data-ng-show="!employees.length">
						<span>No subordinates added yet!</span>
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
