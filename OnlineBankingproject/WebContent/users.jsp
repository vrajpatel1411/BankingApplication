<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Login</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="navigation1">
    <ul>
    <li><a href="users.jsp">Login as a Users</a></li>
    <li><a href="admin.jsp">Login as a admin</a></li>
    </ul>
</div>
<hr>
<h1>User Login</h1>
<hr>
<br><br>
<% String msg = (String)request.getAttribute("msg");
if (msg!=null){%>
<h6><b><%= msg%></b></h6>
<%} %>
<form action="userlogin" method="POST">
	<div>
	<label><b>Account Number:</b></label><br><input type="text" name="pn" placeholder="Enter only numbers" autofocus required><br><br>
	</div>
	<div>
	<label><b>Username:</b></label><br><input type="text" name="un" placeholder="username" required><br><br>
	</div>
	<div>
	<label><b>Password:</b></label><br><input type="password" name="password" required><br><br>
	</div>
	<Button type="submit" Value="Create Account">Login</Button>
</form>
</body>
</html>