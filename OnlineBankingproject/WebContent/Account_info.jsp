<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
</div>
<br>
<br>
</body>
</html>


