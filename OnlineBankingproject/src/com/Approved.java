package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;


import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Approved
 */
@WebServlet("/Approved")
public class Approved extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Approved() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		String id=request.getParameter("id");
		id=id.replaceAll("\\s", "");
		long id1=Long.parseLong(id);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root","Vraj1411");
			Statement st=con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM temp_users where Account_id="+id1);
			String Username=request.getParameter("un");
			String Name="";
			String Mobile_number="";
			String Address="";
			String Email_id="";
			String Password="";
			while(rs.next()) {
				Name=rs.getString("Name");
				Mobile_number=rs.getString("Mobile_number");
				Address=rs.getString("Address");
				Email_id=rs.getString("Email_id");
				Password=rs.getString("Password");
			}
			
			
			String balance="0";
//			PreparedStatement s = con.prepareStatement("insert into balance(Account_id,Username,balance) valuesvalues('\"+id1+\"','\"+Username+\"','\"+balance+\"')");
//			stat.executeUpdate(" INSERT INTO userdata(firstname,lastname,address,state,country,gender) values('"+fn+"','"+ln+"','"+add+"','"+cntry+"','"+st+"','"+gen+"') ");
			st.executeUpdate("insert into temp_users1(Account_id,Name,Mobile_number,Address,Email_id,User_name,Password) values('"+id1+"','"+Name+"','"+Mobile_number+"','"+Address+"','"+Email_id+"','"+Username+"','"+Password+"')");
			
			st.executeUpdate("insert into balance(Account_id,Username,balance) values('"+id1+"','"+Username+"','"+balance+"')");
//			s.close();
			PreparedStatement stmt = con.prepareStatement("DELETE from temp_users where Account_id=?");
		    stmt.setLong(1, id1);
		    stmt.executeUpdate();
		    stmt.close();
		    response.sendRedirect("admin2.jsp");
			
		}
		catch(Exception e) {
		
			response.sendRedirect("admin.jsp");
		}
		}
		catch(Exception e) {
		//	System.out.println(e);
			response.sendRedirect("admin.jsp");
		}
	}

}
