<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<title>Vote Now</title>
	<link rel="stylesheet" href="style.css">
	<meta name="google-signin-client_id" content="1067440508780-4sohls7be8loq4fc61g54pp4211j63h8.apps.googleusercontent.com" charset="ISO-8859-1">

	<script src="https://apis.google.com/js/client:platform.js?onload=renderButton" async defer></script>
	<script src="vote.js"></script>
</head>

<body>

	<div class="bg">
	
		<div class="logo">
			<img src="logo.png">
			<h3>Developed By: Manish Singh(A-607)</h3>
		</div>
		
		<div class="container">
			<h2 class="head">Welcome to the Xth Floor Representative Election</h2>
			<div class="form">
				<form action="index">
					<ul style="list-style-type:none">
						<dl>
							<dt><h2 class="can"><input type="radio" name="vote_sub" value="can1"/>Candidate1</h2></dt>
							<h3 class="des"><dd>about candidate about candidate</dd></h3>
							
							<dt><h2 class="can"><input type="radio" name="vote_sub" value="can2"/>Candidate2</h2></dt>
							<h3 class="des"><dd>about candidate about candidate</dd></h3>
							
							<dt><h2 class="can"><input type="radio" name="vote_sub" value="can3"/>Candidate3</h2></dt>
							<h3 class="des"><dd>about candidate about candidate</dd></h3>
						</dl>
						<input type="hidden" name="idToken" id="gid" value=""/>
						<input type="submit" id="sub">
					</ul>
				</form>
			</div>
			
			<div class="mass"><h3>SignIn To Submit Your Vote</h3></div>
			
			<div id="gSignIn"></div>
			
			<a href="#" onclick="signOut();" id="SigO">Sign Out</a>
		
		</div>
		
	</div>		
	
</body>
</html>