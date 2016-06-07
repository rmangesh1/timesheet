<div class="subnavbar">

	<div class="subnavbar-inner">
	
		<div class="container">
			
			<a href="javascript:;" class="subnav-toggle" data-toggle="collapse" data-target=".subnav-collapse">
		      <span class="sr-only">Toggle navigation</span>
		      <i class="icon-reorder"></i>
		      
		    </a>

			<div class="collapse subnav-collapse">
				<ul class="mainnav">
				
					<!-- <li class="">
						<a href="./index.html">
							<i class="icon-home"></i>
							<span>Home</span>
						</a>	    				
					</li> -->
					
					<li class="dropdown" data-ng-class="{ active: isActive('/organization/department/project/role/activity/tsFactor/activityMapping/employee/grades')}" data-ng-show="employeeObj.userRole.userRole == 'ROLE_ADMIN'">					
						<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
							<i class="icon-th"></i>
							<span data-ng-if="employeeObj.userRole.userRole == 'ROLE_ADMIN'">Admin</span>
							<span data-ng-if="employeeObj.userRole.userRole == 'ROLE_MANAGER'">Assign Tasks</span>
							<b class="caret"></b>
						</a>	    
					
						<ul class="dropdown-menu">
							<li data-ng-show="employeeObj.userRole.userRole == 'ROLE_ADMIN'"><a href="#organization"><i class="icon-edit"></i>&nbsp;Organization</a></li>
							<li data-ng-show="employeeObj.userRole.userRole == 'ROLE_ADMIN'"><a href="#department"><i class="icon-edit"></i>&nbsp;Departments</a></li>
							<li data-ng-show="employeeObj.userRole.userRole == 'ROLE_ADMIN'"><a href="#project"><i class="icon-edit"></i>&nbsp;Projects</a></li>
							<li data-ng-show="employeeObj.userRole.userRole == 'ROLE_ADMIN'"><a href="#employee"><i class="icon-edit"></i>&nbsp;Employees</a></li>
							<li data-ng-show="employeeObj.userRole.userRole == 'ROLE_ADMIN'"><a href="#grades"><i class="icon-edit"></i>&nbsp;Grades</a></li>
							<li data-ng-show="employeeObj.userRole.userRole == 'ROLE_ADMIN'"><a href="#role"><i class="icon-edit"></i>&nbsp;Roles</a></li>
							<li data-ng-show="employeeObj.userRole.userRole == 'ROLE_ADMIN' || employeeObj.userRole.userRole == 'ROLE_MANAGER'"><a href="#activity"><i class="icon-edit"></i>&nbsp;Activities</a></li>
							<li data-ng-show="employeeObj.userRole.userRole == 'ROLE_ADMIN'"><a href="#tsFactor"><i class="icon-edit"></i>&nbsp;Timesheet Factors</a></li>
							<li data-ng-show="employeeObj.userRole.userRole == 'ROLE_ADMIN' || employeeObj.userRole.userRole == 'ROLE_MANAGER'"><a href="#activityMapping"><i class="icon-edit"></i>&nbsp;Activity Mapping</a></li>
						</ul> 				
					</li>
					
					<li class="" data-ng-class="{ active: isActive('/search/profile/viewEmployee')}">					
						<a href="#search">
							<i class="icon-search"></i>
							<span>Search</span>
						</a>	    
					
					</li>
					
					<li class="dropdown" data-ng-class="{ active: isActive('/viewSubordinates/fillTimesheet/viewTimesheets')}">					
						<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
							<i class="icon-time"></i>
							<span>Time Sheet</span>
							<b class="caret"></b>
						</a>	
					
						<ul class="dropdown-menu">
							<li><a href="#fillTimesheet">Fill Timesheet</a></li>
							<li data-ng-show="employeeObj.userRole.userRole == 'ROLE_MANAGER'"><a href="#viewSubordinates">View Timesheet</a></li>
						</ul>    				
					</li>
				
				</ul>
			</div> <!-- /.subnav-collapse -->

		</div> <!-- /container -->
	
	</div> <!-- /subnavbar-inner -->

</div> <!-- /subnavbar -->