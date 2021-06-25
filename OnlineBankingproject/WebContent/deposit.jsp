<%@ page import= "java.sql.Connection,java.util.* ,java.sql.DriverManager,java.sql.ResultSet,java.sql.Statement,java.sql.PreparedStatement,java.sql.Connection" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Deposit</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>Welcome, ${sessionScope.un}</h1>

<h6>Account Number:- ${sessionScope.ac}</h6>
<div id="navigation2">
    <ul>
    <li><a href="balance.jsp">View Balance</a></li>
    <li><a href="withdraw.jsp">Withdraw</a></li>
    <li><a href="deposit.jsp">Deposit</a></li>
    <li><a href="send.jsp">Send Money</a></li>
    <li><a href="view_transaction.jsp">View Latest Transaction</a>
    <li><a href="logout">Logout</a>
    </ul>
</div><br>
<br>
<%String m=(String)request.getAttribute("msg"); 
if(m!=null){%>
<p style="text-align:center;"><b><%=m %></b></p>
<%} %>
<br><br>
<form action="deposit" method="post">
	<div>
	<label><b>Account Number:</b></label><br><input type="text" name="pn" placeholder="Enter only numbers"required><br><br>
	</div>
	<div>
	<label><b>Username:</b></label><br><input type="text" name="un" placeholder="username" required><br><br>
	</div>
	<div>
	<label><b>Password:</b></label><br><input type="password" name="password" required><br><br>
	</div>
	<div>
	<label><b>Deposit Amount:</b></label><br><input type="text" name="wa" required><br><br>
	</div>
	
	<Button type="submit" Value="Create Account">Deposit</Button>
	
</form>

</body>
</html>