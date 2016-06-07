<div class="holder">

<jsp:include page="./navbar.jsp"></jsp:include>

<jsp:include page="./subnavbar.jsp"></jsp:include>    
    
    
<div class="main">

    <div class="container">


      <div class="row" data-ng-app="gradeApp" data-ng-controller="gradeController">
      	
      	<div class="col-md-12">
      		
      		<div class="widget stacked">

      			<div class="widget-header">
					<i class="icon-ok"></i>
					<h3>Grade</h3>
				</div> <!-- /widget-header -->
				
				<div class="widget-content">

					<h3>Grade Primary Details</h3>
					<input type="hidden" id="orgId" value="81" />
					
					<form action="" id="grade-form" role="form" class="form-horizontal">


						<br />

						<fieldset>
						<div class="col-lg-4">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Grade</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control required" name="grade" id="grade" data-ng-model="newGrade.grade">
								</div>
						    </div>
						</div>

					    <div class="col-lg-4">
					    <div class="form-group">
						    <label for="name" class="col-lg-10">Grade Description</label>
							<div class="col-lg-10">
						        <input type="text" class="form-control" name="gradeDescription" id="gradeDescription" data-ng-model="newGrade.gradeDescription">
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
					<div data-ng-show="grades.length">
						<div class="table-responsive">
						<table class="table table-bordered table-hover table-striped">
							<thead>
							  <tr>
								<th>Grade</th>
								<th>Grade Description</th>
								<th></th>
							  </tr>
							</thead>
							<tbody>
							  <tr data-ng-repeat="grade in grades">
								<td class="col-md-4">{{grade.grade}}</td>
								<td class="col-md-5">{{grade.gradeDescription}}</td>
								<td class="col-md-3">
								<button class="btn btn-info" data-ng-click="edit(grade.id)"><i class="icon-edit"></i>&nbsp;Edit</button>&nbsp;&nbsp;&nbsp;
								<button class="btn btn-warning" data-ng-click="deleteGrade(grade.id)"><i class="icon-trash"></i>&nbsp;Delete</button>
								</td>
							  </tr>
							</tbody>
						  </table>
					  </div>
					</div>
					<div class="col-lg-10" data-ng-show="!grades.length">
						<span>No grades added yet!</span>
					</div>
				</div> <!-- /widget-content -->
					
			</div> <!-- /widget -->					
			
	    </div> <!-- /col-md-12 -->     	
      	
      	
      </div> <!-- /row -->

    </div> <!-- /container -->
    
</div> <!-- /main -->
    

 
<jsp:include page="./footer.jsp"></jsp:include>
    
</div>

