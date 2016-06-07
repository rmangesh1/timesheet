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
	
	<div class="content clearfix" data-ng-controller="LoginController">
		
		<form id="login-form" action="" >
		
			<h1>Sign In</h1>		
			
			<div class="login-fields">
				
				<p>Sign in using your registered account:</p>
				
				<div class="field">
					<img src="./img/signin/user.png" class="login-img"/>
					<label for="username">Username:</label>
					
					<input type="text" id="username" name="username" placeholder="Username" class="form-control input-lg username-field" data-ng-model="loginData.username">
				</div> <!-- /field -->
				
				<div class="field">
					<img src="./img/signin/password.png" class="login-img"/>
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