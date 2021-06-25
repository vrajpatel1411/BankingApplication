package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class withdraw
 */
@WebServlet("/withdraw")
public class withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public withdraw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
			// TODO Auto-generated method stub
			HttpSession session = request.getSession();
			long Account_number=Long.parseLong(request.getParameter("pn"));
			String username=(String)request.getParameter("un");
			String password=request.getParameter("password");
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
			System.out.println(ac);
			if(Account_number==ac) {
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
				else {
					p=null;
				}
				System.out.println(password.equals(p));
				if(p==null)
				{
					msg="wrong account id or username";
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("withdraw.jsp").forward(request,response);
				}
				
				if(password.equals(p))
				{
					
					int wa=Integer.parseInt(request.getParameter("wa"));
					PreparedStatement ps=con.prepareStatement("select balance from balance where Username=?");
					ps.setString(1,username);
					ResultSet rs1=ps.executeQuery(); 
					int balance=0;
					while(rs1.next()) {
					balance=Integer.parseInt(rs1.getString("balance"));
					}
					if(balance > wa)
					{
						balance=balance-wa;
						String wa1=Integer.toString(wa);
						String wa2="-"+wa1;
						PreparedStatement ps1=con.prepareStatement("Update balance SET balance = ? WHERE Username=?");
						ps1.setInt(1, balance);
						ps1.setString(2, username);
						int i=ps1.executeUpdate();
						String account="self";
						String account1="self";
						Calendar calendar = Calendar.getInstance();
						java.util.Date currentTime = calendar.getTime();
						PreparedStatement ps2=con.prepareStatement("Insert into transaction1(Account_id,from_account,to_account,Date,Amount) values(?,?,?,CURRENT_TIMESTAMP,?)");
						ps2.setLong(1,Account_number);
						ps2.setString(2, account);
						ps2.setString(3, account1);
						ps2.setString(4, wa2);
//						ps2.setTimestamp(4, new Timestamp(currentTime.getTime()));
						ps2.execute();
						
						msg="Money withdraw";
						request.setAttribute("msg", msg);
						request.getRequestDispatcher("withdraw.jsp").forward(request, response);
					}
					else {
					msg="Not sufficient balance";
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("withdraw.jsp").forward(request, response);
				    }
				}
				else {
					msg="Username or password is wrong";
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("withdraw.jsp").forward(request,response);
					
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
	      		msg="wrong account id or username";
	      		request.setAttribute("msg", msg);
				request.getRequestDispatcher("withdraw.jsp").forward(request,response);
			}}
			else {
				
				String msg="wrong account number you are trying";
			     request.setAttribute("msg", msg);
				request.getRequestDispatcher("withdraw.jsp").forward(request,response);
				
			}
	}

}
