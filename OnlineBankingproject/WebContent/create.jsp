<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
<hr>
<h1>Registration form</h1>
<hr>
<br>
<br>
<% String msg = (String)request.getAttribute("msg");
if (msg!=null){%>
<p><b><%= msg%></b></p>
<%} %>
<form action="createaccount" method="POST">
	<div>
	<label><b>Account Number:</b></label><br><input type="text" name="pn" placeholder="Enter only numbers" Autofocus required><br><br>
	</div>
	<div>
	<label><b>Name:</b></label><br><input type="text" name="name" placeholder="enter name" required><br><br>
	</div>
	<div>
	<label><b>Mobile Number:</b></label><br><input type="text" name="mobile_no" placeholder="enter mobile number" required ><br><br>
	</div>
	<div>
	<label><b>Address:</b></label><br><input type="text" name="address" placeholder="Address" required><br><br>
	</div>
	<div>
	<label><b>Email Id:</b></label><br><input type="email" name="em" placeholder="Email id" required><br><br>
	</div>
	<div>
	<label><b>Username:</b></label><br><input type="text" name="un" placeholder="username" required><br><br>
	</div>
	<div>
	<label><b>Password:</b></label><br><input type="password" name="password" required><br><br>
	</div>
	<div>
	<label><b>Re-Enter Password:</b></label><br><input type="password" name="rpassword" required><br><br>
	</div>
	
	
	<Button type="submit" Value="Create Account">Create Account</Button>
	
</form>
</body>
</html>