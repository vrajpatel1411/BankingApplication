<%@ page import= "java.sql.Connection,java.util.* ,java.sql.DriverManager,java.sql.ResultSet,java.sql.Statement,java.sql.PreparedStatement,java.sql.Connection" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transactions</title>
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
<br>
<br>
<table style="border:1px solid black;border-collapse:collapse;margin-left: auto;margin-right: auto;">
						<tr style="border:1px solid black;border-collapse:collapse;margin-left: auto;margin-right: auto;">
							<th style="border:1px solid black;border-collapse:collapse;padding:5px;">Transaction id</th>
							<th style="border:1px solid black;border-collapse:collapse;padding:5px;">Account Id</th>
							<th style="border:1px solid black;border-collapse:collapse;padding:5px;">From_account</th>
							<th style="border:1px solid black;border-collapse:collapse;padding:5px;">To_account</th>
							<th style="border:1px solid black;border-collapse:collapse;padding:5px;">Amount</th>
							<th style="border:1px solid black;border-collapse:collapse;padding:5px;">Date</th>
						</tr>
<% try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root","Vraj1411");
				Statement st=con.createStatement();
				Object a=session.getAttribute("ac");
				long ac=0;
				if(a != null) {
					  if(a instanceof Long)
					  {
					    ac = ((Long)a).longValue();
					  }
					  else if(a instanceof String) {
					    ac = Long.parseLong((String)a);
					  }
				}
				PreparedStatement ps=con.prepareStatement("select * from transaction1 where Account_id=?");
				ps.setLong(1,ac);
				ResultSet rs=ps.executeQuery(); 
				while(rs.next()){
			%>
				<tr style="border:1px solid black;border-collapse:collapse;margin-left: auto;margin-right: auto;">
							<td style="border:1px solid black;border-collapse:collapse;padding:5px;"><%=rs.getLong("transaction_id")%></td>
							<td style="border:1px solid black;border-collapse:collapse;padding:5px;"><%=rs.getString("Account_id") %></td>
							<td style="border:1px solid black;border-collapse:collapse;padding:5px;"><%=rs.getString("From_account") %></td>
							<td style="border:1px solid black;border-collapse:collapse;padding:5px;"><%=rs.getString("To_account") %></td>
							<td style="border:1px solid black;border-collapse:collapse;padding:5px;"><%=rs.getString("Amount") %></td>
							<td style="border:1px solid black;border-collapse:collapse;padding:5px;"><%=rs.getString("Date") %></td>					
							
						</tr>	
			<%} }catch(Exception e){System.out.println(e);}%>
				</table>
</body>
</html>