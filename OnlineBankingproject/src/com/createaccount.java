package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/createaccount")
public class createaccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
   

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		response.sendRedirect("welcome.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		long Account_number=Long.parseLong(request.getParameter("pn"));
		String name=request.getParameter("name");
		String mobile_number=request.getParameter("mobile_no");
		String address=request.getParameter("address");
		String emailid=request.getParameter("em");
		String username=request.getParameter("un");
		String password=request.getParameter("password");
		String rpassword=request.getParameter("rpassword");
		String msg="";
		request.setAttribute("msg", msg);
		if(password.equals(rpassword))
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root","Vraj1411");
				PreparedStatement st1 = con.prepareStatement("insert into users_track1 values(?,?)");
				st1.setLong(1, Account_number);
				st1.setString(2, username);
				st1.close();
				PreparedStatement st = con.prepareStatement("insert into temp_users values(?,?,?,?,?,?,?)");
				st.setLong(1, Account_number);
				st.setString(2, name);
				st.setString(3,mobile_number);
				st.setString(4,address );
				st.setString(5, emailid);
				st.setString(6, username);
				st.setString(7,password);
				st.executeUpdate();
				st.close();
				con.close();
				response.sendRedirect("welcome1.jsp");
			}
			catch(Exception e){
				System.out.println(e);
				msg="Username or Account id Already in Use";
				System.out.println(e);
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("create.jsp").forward(request,response);
				
			}
			
		}
		else {
			msg="Password not match";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("create.jsp").forward(request,response);
			
		}
	}

}

