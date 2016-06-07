<div class="holder">

<jsp:include page="./navbar.jsp"></jsp:include>

<jsp:include page="./subnavbar.jsp"></jsp:include>    
    
    
<div class="main">

    <div class="container">


      <div class="row" data-ng-app="empProfileApp" data-ng-controller="employeeProfileController">
      	
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
						      <button class="btn btn-success profileEditable" data-ng-click="saveRecord()"><i class="icon-ok"></i> Update</button>&nbsp;&nbsp;
						  </div>
						    </div>
							</div>
							
						</fieldset>

					</form>
						<div class="col-lg-10" id="wsResponse"></div>
						
				
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

$("form :input").not(".profileEditable").attr("disabled","disabled");

</script>