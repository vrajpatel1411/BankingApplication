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

/**
 * Servlet implementation class adminlogin
 */
@WebServlet("/adminlogin")
public class adminlogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminlogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String username=request.getParameter("un");
		String password=request.getParameter("password");
		session.setAttribute("admin", username);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root","Vraj1411");
			Statement st=con.createStatement();
			String Query="select Password from admin where Username=\""+username+"\"";
			ResultSet rs=st.executeQuery(Query);
			String p="";
			if(rs.next()) {
			p=rs.getString("Password");
			}
			String msg="";
			request.setAttribute("msg",msg);
			//System.out.println(p);
			if(password.equals(p))
			{
				response.sendRedirect("admin2.jsp");
			}
			else {
				msg="password is wrong";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("admin.jsp").forward(request,response);
				
			}
		}
		catch(Exception e)
		{
			//System.out.println(e);
      		String msg="wrong Username";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("admin.jsp").forward(request,response);
		}

	}

}
