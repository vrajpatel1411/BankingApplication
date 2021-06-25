package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




@WebServlet("/userlogin")
public class userlogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		long Account_number=Long.parseLong(request.getParameter("pn"));
		String username=(String)request.getParameter("un");
		String password=request.getParameter("password");
		request.setAttribute("ac",Account_number);
		session.setAttribute("un",username);
		session.setAttribute("ac", Account_number);
		String msg=" ";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root","Vraj1411");
			Statement st=con.createStatement();
			String Query="select Password from temp_users1 where User_name=\""+username+"\"and Account_id=\""+Account_number+"\"";
			//System.out.println(Query);
			//System.out.println('select Password from temp_users where Username='""+username+""+' '+'AND Account_id='+Account_number);
			
			ResultSet rs=st.executeQuery(Query);
			String p="";
			if(rs.next()) {
			p=rs.getString("Password");
			}
			
			if(p.equals(""))
			{
				msg="wrong account id or username";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("users.jsp").forward(request,response);
			}
			
			if(password.equals(p))
			{
				response.sendRedirect("Account_info.jsp");
			}
			else {
				System.out.println("Password wrong=>"+p);
				msg="password is wrong";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("users.jsp").forward(request,response);
				
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
      		msg="wrong account id or username";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("users.jsp").forward(request,response);
		}
	}

}
