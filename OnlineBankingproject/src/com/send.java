package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

/**
 * Servlet implementation class send
 */
@WebServlet("/send")
public class send extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public send() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		session.setAttribute("un",username);
		session.setAttribute("ac", Account_number);
		String msg=" ";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/banking","root","vraj1411");
			Statement st=con.createStatement();
			String Query="select Password from users where Username=\""+username+"\"";
			//System.out.println(Query);
			//System.out.println('select Password from temp_users where Username='""+username+""+' '+'AND Account_id='+Account_number);
			
			ResultSet rs=st.executeQuery(Query);
			String p="";
			if(rs.next()) {
			p=rs.getString("Password");
			}
			System.out.println(password.equals(p));
			if(p==null)
			{
				msg="wrong account id or username";
				session.setAttribute("msg", msg);
				request.getRequestDispatcher("send.jsp").forward(request,response);
			}
			
			if(password.equals(p))
			{
				try {
					long rAccount_number=Long.parseLong(request.getParameter("rpn"));
					PreparedStatement ps5=con.prepareStatement("select balance from balance where Account_id=?");
					ps5.setLong(1,rAccount_number);
					
					ResultSet rs1=ps5.executeQuery();
					int receiver_balance=0;
					while(rs1.next()) {
						receiver_balance=Integer.parseInt(rs1.getString("balance"));
					}
					System.out.println(receiver_balance);
					PreparedStatement ps=con.prepareStatement("select balance from balance where Username=?");
					ps.setString(1,username);
					ResultSet rs2=ps.executeQuery(); 
					int sender_balance=0;
					while(rs2.next()) {
					sender_balance=Integer.parseInt(rs2.getString("balance"));
					}

					int wa=Integer.parseInt(request.getParameter("wa"));
					int wa1=wa;
					if(sender_balance>wa) {
					sender_balance=sender_balance-wa;
					receiver_balance=receiver_balance+wa;
					String wa2=Integer.toString(wa);
					String wa3="-"+wa2;
					String wa4="+"+wa2;
					PreparedStatement ps1=con.prepareStatement("Update balance SET balance = ? WHERE Username=?");
					ps1.setInt(1,sender_balance);
					ps1.setString(2, username);
					ps1.executeUpdate();
					
					PreparedStatement ps2=con.prepareStatement("Update balance SET balance = ? WHERE Account_id=?");
					ps2.setInt(1,receiver_balance);
					ps2.setString(2,Long.toString(rAccount_number));
					ps2.executeUpdate();
					String account = "self";
					String account1=Long.toString(rAccount_number);
					
					
					PreparedStatement ps3=con.prepareStatement("Insert into transaction1(Account_id,from_account,to_account,Date,Amount) values(?,?,?,CURRENT_TIMESTAMP,?)");
					ps3.setLong(1,Account_number);
					ps3.setString(2, account);
					ps3.setString(3, account1);
					ps3.setString(4, wa3);
//					ps2.setTimestamp(4, new Timestamp(currentTime.getTime()));
					ps3.execute();
					
					String account2=Long.toString(rAccount_number);
					String account3=Long.toString(Account_number);
					PreparedStatement ps4=con.prepareStatement("Insert into transaction1(Account_id,from_account,to_account,Date,Amount) values(?,?,?,CURRENT_TIMESTAMP,?)");
					ps3.setLong(1,rAccount_number);
					ps3.setString(2, account2);
					ps3.setString(3, account3);
					ps3.setString(4, wa4);
//					ps2.setTimestamp(4, new Timestamp(currentTime.getTime()));
					ps3.execute();
					msg="Money Sent";
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("send.jsp").forward(request,response);
					}
					else{
						msg="Insufficient balance";
						request.setAttribute("msg", msg);
						request.getRequestDispatcher("send.jsp").forward(request,response);
						
					}
				}
				catch(Exception e) {
					System.out.println(e);
					msg="Receiver account not found";
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("send.jsp").forward(request,response);
				}
			}
			else {
				msg="Username or password is wrong";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("send.jsp").forward(request,response);
				
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
      		msg="wrong account id or username";
      		request.setAttribute("msg", msg);
			request.getRequestDispatcher("send.jsp").forward(request,response);
		}}
		else {
			
			String msg="wrong account number you are trying";
		     request.setAttribute("msg", msg);
			request.getRequestDispatcher("send.jsp").forward(request,response);
			
		}
}



	}


