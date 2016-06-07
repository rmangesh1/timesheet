<%-- <html lang="en"><head>
    <meta charset="utf-8">
    <title>Login :: Base Admin</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes"> 
    
    <link href="./css/pages/signin.css" rel="stylesheet" type="text/css">
    
	<jsp:include page="./pagecss.jsp"></jsp:include>
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-route.js"></script> -->
	
    <script src="js/login.js"></script>

</head>

<body>
	
<nav class="navbar navbar-inverse" role="navigation">

	<div class="container">
  <!-- Brand and toggle get grouped for better mobile display -->
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="./login.jsp">EMS - Login</a>
  </div>

</div> <!-- /.container -->
</nav>



<div class="account-container stacked">
	
	<div class="content clearfix" data-ng-app="loginApp" data-ng-controller="LoginController">
		
		<form id="login-form" action="" >
		
			<h1>Sign In</h1>		
			
			<div class="login-fields">
				
				<p>Sign in using your registered account:</p>
				
				<div class="field">
					<label for="username">Username:</label>
					<input type="text" id="username" name="username" placeholder="Username" class="form-control input-lg username-field" data-ng-model="loginData.username">
				</div> <!-- /field -->
				
				<div class="field">
					<label for="password">Password:</label>
					<input type="password" id="password" name="password" placeholder="Password" class="form-control input-lg password-field" data-ng-model="loginData.password">
				</div> <!-- /password -->
				
			</div> <!-- /login-fields -->
			<div class="col-lg-10" id="wsResponse"></div>
			<div class="login-actions">
				
				<span class="login-checkbox">
					<input id="Field" name="Field" type="checkbox" class="field login-checkbox" value="First Choice" tabindex="4">
					<label class="choice" for="Field">Keep me signed in</label>
				</span>
									
				<button class="login-action btn btn-primary" data-ng-click="loginEms()">Sign In</button>
				
			</div> <!-- .actions -->
			
		<!-- 	<div class="login-social">
				<p>Sign in using social network:</p>
				
				<div class="twitter">
					<a href="#" class="btn_1">Login with Twitter</a>				
				</div>
				
				<div class="fb">
					<a href="#" class="btn_2">Login with Facebook</a>				
				</div>
			</div> -->
			
		</form>
	</div> <!-- /content -->
	
</div> <!-- /account-container -->


<!-- Text Under Box -->
<!-- <div class="login-extra">
	Don't have an account? <a href="./signup.html">Sign Up</a><br>
	Remind <a href="#">Password</a>
</div> --> <!-- /login-extra -->



<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="./js/jquery-ui-1.10.0.custom.min.js"></script>
<script src="./js/bootstrap.min.js"></script>

<script src="./js/Application.js"></script>

<script src="./js/signin.js"></script>

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

<a id="back-to-top" href="#top" style="display: none;"><i class="icon-chevron-up"></i></a></body></html> --%>