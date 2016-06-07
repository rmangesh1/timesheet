<div class="holder">

<jsp:include page="./navbar.jsp"></jsp:include>

<jsp:include page="./subnavbar.jsp"></jsp:include>    
    
    
<div class="main">

    <div class="container">


      <div class="row" data-ng-app="actApp" data-ng-controller="activityController">
      	
      	<div class="col-md-12">
      		
      		<div class="widget stacked">

      			<div class="widget-header">
					<i class="icon-ok"></i>
					<h3>Activity</h3>
				</div> <!-- /widget-header -->
				
				<div class="widget-content">

					<h3>Activity Primary Details</h3>
					<input type="hidden" id="orgId" value="1" />
					
					<form action="" id="activity-form" role="form" class="form-horizontal">


						<br />

						<fieldset>
						<div class="col-lg-4">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Activity Name</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control required" name="activityName" id="activityName" data-ng-model="newActivity.activityName">
								</div>
						    </div>
						</div>

						<div class="col-lg-4">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Activity Code</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control required" name="activityCode" id="activityCode" data-ng-model="newActivity.activityCode">
								</div>
						    </div>
						</div>
						    
						    
					    <div class="col-lg-4">
					    <div class="form-group">
						    <label for="name" class="col-lg-10">Activity Description</label>
							<div class="col-lg-10">
						        <input type="text" class="form-control" name="activityDescription" id="activityDescription" data-ng-model="newActivity.activityDescription">
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
					<div data-ng-show="activities.length">
						<div class="table-responsive">
						<table class="table table-bordered table-hover table-striped">
							<thead>
							  <tr>
								<th>Activity Name</th>
								<th>Activity Code</th>
								<th>Activity Description</th>
								<th></th>
							  </tr>
							</thead>
							<tbody>
							  <tr data-ng-repeat="activity in activities">
								<td class="col-md-3">{{activity.activityName}}</td>
								<td class="col-md-3">{{activity.activityCode}}</td>
								<td class="col-md-4">{{activity.activityDescription}}</td>
								<td class="col-md-2">
								<button class="btn btn-info" data-ng-click="edit(activity.id)"><i class="icon-edit"></i>&nbsp;Edit</button>&nbsp;&nbsp;&nbsp;
								<button class="btn btn-warning" data-ng-click="deleteActivity(activity.id)"><i class="icon-trash"></i>&nbsp;Delete</button>
								</td>
							  </tr>
							</tbody>
						  </table>
					  </div>
					</div>
					<div class="col-lg-10" data-ng-show="!activities.length">
						<span>No activities added yet!</span>
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