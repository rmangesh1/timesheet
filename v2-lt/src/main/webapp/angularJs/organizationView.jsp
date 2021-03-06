<div class="holder">

<jsp:include page="./navbar.jsp"></jsp:include>

<jsp:include page="./subnavbar.jsp"></jsp:include>    
    
    
<div class="main">

    <div class="container">


      <div class="row" data-ng-app="orgApp" data-ng-controller="organizationController">
      	
      	<div class="col-md-12">
      		
      		<div class="widget stacked">

      			<div class="widget-header">
					<i class="icon-ok"></i>
					<h3>Organization</h3>
				</div> <!-- /widget-header -->
				
				<div class="widget-content">

					<h3>Organization Details</h3>
					<input type="hidden" id="orgId" value="1" />
					
					<form action="/" id="organization-form" role="form" class="form-horizontal">
						
						
						<br />

						<fieldset>
						
						<div class="col-lg-10"><h4>Organization main details</h4></div>
						<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Organization Name</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control required" name="orgName" id="orgName" readonly="readonly" data-ng-model="organization.orgName">
								</div>
						    </div>
						</div>

						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Organization Company</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control required" name="company" id="company" data-ng-model="organization.company">
								</div>
						    </div>
						</div>
						<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
						
						<div class="col-lg-10"><h4>Organization contact details</h4></div>
						<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Primary Phone</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control required" name="primaryPhone" id="primaryPhone" data-ng-model="organization.primaryPhone">
								</div>
						    </div>
						</div>
						
						

						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Primary Email</label>
								<div class="col-lg-10">
							        <input type="email" class="form-control required" name="primaryEmail" id="primaryEmail" data-ng-model="organization.primaryEmail">
								</div>
						    </div>
						</div>
						
						<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
						<div class="col-lg-10"></div>
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Primary Mobile</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control required" name="mobile" id="mobile" data-ng-model="organization.mobile">
								</div>
						    </div>
						</div>
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Website</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control" name="website" id="website" data-ng-model="organization.website">
								</div>
						    </div>
						</div>
						
						<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
						<div class="col-lg-10"></div>
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Fax</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control" name="fax" id="fax" data-ng-model="organization.fax">
								</div>
						    </div>
						</div>
						    
						    <div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
							<div class="col-lg-10"></div>
							
							<div class="col-lg-8">
						    <div class="form-group">

						    	<div class="col-lg-8">
						      <button class="btn btn-success" data-ng-click="saveRecord()"><i class="icon-ok"></i> Save or update</button>&nbsp;&nbsp;
						  </div>
						    </div>
							</div>
						</fieldset>

					</form>
						<div class="col-lg-10" id="wsResponse"></div>
						<br/><br/>
						
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