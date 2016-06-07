<nav class="navbar navbar-inverse" role="navigation">

	<div class="container">
  <!-- Brand and toggle get grouped for better mobile display -->
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
      <span class="sr-only">Toggle navigation</span>
      <i class="icon-cog"></i>
    </button>
    <a class="navbar-brand" href="./index.html">Employee management system - {{employeeObj.organization.orgName}}</a>
  </div>

  <!-- Collect the nav links, forms, and other content for toggling -->
  <div class="collapse navbar-collapse navbar-ex1-collapse">
    <ul class="nav navbar-nav navbar-right">
  <!--     <li class="dropdown">
						
			<a href="javscript:;" class="dropdown-toggle" data-toggle="dropdown">
				<i class="icon-cog"></i>
				Settings
				<b class="caret"></b>
			</a>
			
			<ul class="dropdown-menu">
				<li><a href="./account.html">Account Settings</a></li>
				<li><a href="javascript:;">Privacy Settings</a></li>
				<li class="divider"></li>
				<li><a href="javascript:;">Help</a></li>
			</ul>
			
		</li>
 -->
		<li class="dropdown">
						
			<a href="javscript:;" class="dropdown-toggle" data-toggle="dropdown">
				<i class="icon-user"></i> 
				{{employeeObj.firstName}} {{employeeObj.lastName}}
				<b class="caret"></b>
			</a>
			
			<ul class="dropdown-menu">
				<li><a href="#profile">My Profile</a></li>
				<li class="divider"></li>
				<li><a href="#login">Logout</a></li>
			</ul>
			
		</li>
    </ul>
    
   <!--  <form class="navbar-form navbar-right" role="search" id="generic-search-form">
      <div class="form-group">
        <input type="text" class="form-control input-sm search-query" placeholder="Search Employee">
        <a data-ng-click="findRecord()" id="generic-search-btn"><i class="icon-search"></i></a>
      </div>
    </form> -->
  </div><!-- /.navbar-collapse -->
</div> <!-- /.container -->
</nav>