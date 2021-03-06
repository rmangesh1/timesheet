<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>EMS</title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">    
    
    <jsp:include page="./pagecss.jsp"></jsp:include>
    <script src="js/search.js"></script>

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
									<button class="btn btn-info" data-ng-click="view(employee.id)"><i class="icon-eye-open"></i>&nbsp;View</button>&nbsp;&nbsp;&nbsp;
								</td>
							  </tr>
							</tbody>
						  </table>
					  </div>
					</div>
					<div class="col-lg-10" data-ng-show="!employees.length">
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
