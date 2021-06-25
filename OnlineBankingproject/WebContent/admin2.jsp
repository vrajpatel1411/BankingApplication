<%@ page import= "java.sql.Connection,java.util.* ,java.sql.DriverManager,java.sql.ResultSet,java.sql.Statement,java.sql.PreparedStatement,java.sql.Connection" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin panel</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>welcome,${sessionScope.admin}</h1>

<p><b>Users Details:-</b></p>
<table style="border:1px solid black;border-collapse:collapse;margin-left: auto;margin-right: auto;">
						<tr style="border:1px solid black;border-collapse:collapse;padding:5px;">
							<th style="border:1px solid black;border-collapse:collapse;padding:5px;">Account id</th>
							<th style="border:1px solid black;border-collapse:collapse;padding:5px;">Name</th>
							<th style="border:1px solid black;border-collapse:collapse;padding:5px;">Mobile number</th>
							<th style="border:1px solid black;border-collapse:collapse;padding:5px;">Address</th>
							<th style="border:1px solid black;border-collapse:collapse;padding:5px;">Email Id</th>
							<th style="border:1px solid black;border-collapse:collapse;padding:5px;">Username</th>
							<th style="border:1px solid black;border-collapse:collapse;padding:5px;">Approved</th>
						</tr>
<% try {
				String un=(String)session.getAttribute("admin");
				if(un!=null){
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root","Vraj1411");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("Select * from temp_users");
				while(rs.next())
				{%>
						
						<tr style="border:1px solid black;border-collapse:collapse;padding:5px;">
							<td style="border:1px solid black;border-collapse:collapse;padding:5px;"><%=rs.getLong("Account_id")%></td>
							<td style="border:1px solid black;border-collapse:collapse;padding:5px;"><%=rs.getString("Name") %></td>
							<td style="border:1px solid black;border-collapse:collapse;padding:5px;"><%=rs.getString("Mobile_number") %></td>
							<td style="border:1px solid black;border-collapse:collapse;padding:5px;"><%=rs.getString("Address") %></td>
							<td style="border:1px solid black;border-collapse:collapse;padding:5px;"><%=rs.getString("Email_id") %></td>
							<td stylestyle="border:1px solid black;border-collapse:collapse;padding:5px;"><%=rs.getString("User_name") %></td>
							<td style="border:1px solid black;border-collapse:collapse;padding:5px;"><button onclick="window.location.href='Approved?id=<%=rs.getLong("Account_id")%>&un=<%=rs.getString("User_name") %>'">Click Here</button></td>
						
							
						</tr>
				
				
				
				
				
				<% }%>
				</table><% 
				}
				
		} catch(Exception e){
			System.out.println(e);
		}%>
</body>
</html>