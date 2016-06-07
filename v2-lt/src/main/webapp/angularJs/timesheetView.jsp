<div class="holder">

<jsp:include page="./navbar.jsp"></jsp:include>

<jsp:include page="./subnavbar.jsp"></jsp:include>    
    
    
<div class="main">

 <div class="timesheet">
<div data-ng-app="timeSheetApp" data-ng-controller="timeSheetController">
      	
					<div class="pull-right" style="margin-right: 82;">
						<button class="btn btn-success" data-ng-click="addEntry()"><i class="icon-plus"></i> Add entry</button>&nbsp;&nbsp;
					</div>
					<h3>Time Sheet Details</h3>
					
					<div class="col-lg-2">
					
					<input id="tsDate" name="tsDate" data-ng-pattern="dd.MM.yyyy" type="text" size="8"
                                   data-ng-model="tsDetailsForm.dayOfWeek" class="form-control" placeholder="Time sheet date"/>
                     </div>
                     <div class="col-lg-1">
                     	<button class="btn btn-success" data-ng-click="findRecord()"><i class="icon-search"></i> Find</button>&nbsp;&nbsp;
                     </div>
                     
                     <div class="col-lg-3">
                     	<label>Week Number : {{tsDetailsForm.weekNum}}</label>
                     </div>
                     <br/>
                     
                     
					
					<input type="hidden" id="orgId" value="1" />
					
					<form action="/" id="timesheet-form" role="form" class="form-horizontal">
						
						
						<br />

						<fieldset>
						
						<div data-ng-repeat="tsRecord in tsDetailsForm.tsRecordForm" data-ng-init="parentIndex = $index">
						<br><br><br><br><br><br>
						
						<jsp:include page="./timeSheet.jsp"></jsp:include>
							
						<div class="col-lg-1">
						    <div class="form-group" style="margin-top: 28px;">
						    	<div class="col-lg-8">
						      <button class="btn btn-info" data-ng-click="deleteEntry($index)"><i class="icon-trash"></i></button>&nbsp;&nbsp;
						  </div>
						    </div>
							</div>
							
						</div>	
						<br><br><br><br><br><br><br><br>
							<div class="col-lg-8">
						    <div class="form-group">

						    	<div class="col-lg-8">
						      <button class="btn btn-success" data-ng-click="saveRecord()"><i class="icon-ok"></i> Save Timesheet</button>&nbsp;&nbsp;
						  </div>
						    </div>
							</div>
							
							
						</fieldset>

					</form>
						<div class="col-lg-10" id="wsResponse"></div>
						<br/><br/>
						
			
      	
    </div>  	
</div>
    
</div> <!-- /main -->
    

 
<jsp:include page="./footer.jsp"></jsp:include>
    
</div>

<script>
    $(function () {
          $("#tsDate").datepicker({
            dateFormat: "dd.mm.yy", showButtonPanel: true, showAnim: "slideDown",
            onSelect: function(selected) {
              //$("#tsDate").datepicker("option","minDate", selected)
            }
        });

    });
</script>

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