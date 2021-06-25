<%@ page import= "java.sql.Connection,java.util.* ,java.sql.DriverManager,java.sql.ResultSet,java.sql.Statement,java.sql.PreparedStatement,java.sql.Connection" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Balance</title>
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

	<% try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root","Vraj1411");
		Statement st=con.createStatement();
		String username=(String)session.getAttribute("un");
		PreparedStatement ps=con.prepareStatement("select balance from balance where Username=?");
		ps.setString(1,username);
		ResultSet rs=ps.executeQuery(); 
		String balance="";
		while(rs.next()) {
		balance=rs.getString("balance");
		}
	%>	
	<h1>Balance=><%=balance %></h1>
	
	
	<% }catch(Exception e){
		System.out.println(e);
	}
	%>


</body>
</html>