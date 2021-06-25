<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin</title>
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
<h1>Admin Login</h1>
<hr>
<br><br>
<% String msg = (String)request.getAttribute("msg");
if (msg!=null){%>
<%= msg%>
<%} %>
<form action="adminlogin" method="post">
	<div>
	<label><b>Username:</b></label><br><input type="text" name="un" placeholder="username" required><br><br>
	</div>
	<div>
	<label><b>Password:</b></label><br><input type="password" name="password" required><br><br>
	</div>
	<Button type="submit" Value="Create Account">Admin login</Button>
</form>
</body>
</html>