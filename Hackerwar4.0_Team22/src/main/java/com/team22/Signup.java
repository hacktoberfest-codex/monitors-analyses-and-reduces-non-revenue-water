package com.team22;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

/**
 * Servlet implementation class Signup
 */
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static int consumer_num;
	static int count=0;
	Connection cn=null;
	PreparedStatement ps=null;
	PreparedStatement ps1=null;
	PreparedStatement ps2=null;
	Statement ps3=null;
	PreparedStatement ps4=null;
	ServletContext context=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
    	System.out.println("Signup servlet object is constructed");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init()  {
		System.out.println("Signup servlet object is initialized");
    	context=getServletContext();
    	String d=context.getInitParameter("driver");
    	String ur=context.getInitParameter("url");
    	String us=context.getInitParameter("username");
    	String pa=context.getInitParameter("password");
    	try 
    	{
    		Class.forName(d);
    		cn=DriverManager.getConnection(ur,us,pa);
    		ps=cn.prepareStatement("insert into login_user values(?,?,?,?,?,?,?)");
    		ps1=cn.prepareStatement("insert into consumer_user values(?,?,?,?,?,?,?,?)");
    		ps2=cn.prepareStatement("select * from consumer_numdata");
    		ps3=cn.createStatement();
    		ResultSet rs=ps2.executeQuery();
            while(rs.next())
            {
            	consumer_num =rs.getInt(1);
            }
    		
    	}
    	catch(ClassNotFoundException ce)
    	{
    		System.out.println("Class not found");
    	}
    	catch(SQLException se)
    	{
    		se.printStackTrace();
    	}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String field1=request.getParameter("user");
		String field2=request.getParameter("phone");
		String field3=request.getParameter("email");
		String field4=request.getParameter("addr");
		String field5=request.getParameter("area");
		String field6=request.getParameter("mem");
		String field7=request.getParameter("pass");
		try
		{
			ps.setString(1,field1);
			ps.setString(2, field2);
			ps.setString(3, field3);
			ps.setString(4, field4);
			ps.setString(5, field5);
			ps.setString(6, field6);
			ps.setString(7, field7);
			consumer_num=consumer_num+1;
			count++;
			ps1.setInt(1, consumer_num);//assign consumer number
			//ps3.executeUpdate("update consumer_numdata set consumerid=consumer_num+2 where slno=1 ");//update in the table
			ps1.setString(2, field7);//password
			ps1.setString(3, field1);//username
			ps1.setString(4, field5);//area
			ps1.setInt(5, 0000);//previous reading
			ps1.setInt(6, 0000);//present reading
			ps1.setInt(7, 10000);//source flow
			ps1.setInt(8, 5000);//destination flow
			ps1.executeUpdate();
			ps.executeUpdate();
			//ResultSet rs=ps2.executeQuery();
//			int con_num = 0;
//			while(rs.next())
//            {
//            	con_num =rs.getInt(1);
//            }
			out.println("<html><body style='background-image: url(./back9.jpg);\r\n"
					+ "background-size: cover; color: blue; font-size: 30px;\r\n"
					+ "text-align: center;'>");
			out.println("<br><br><b>Welcome to DROP BY DROP portal</b>");
			out.println("<br><br><b>Your Consumer Number is </b>"+consumer_num);
			out.println("<br><a href='http://localhost:8082/Hackerwar4.0_Team22/login.html'>Login</a>");
			out.println("</body></html>");
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
	@Override
	public void destroy()
	{
		try
		{
			//ps3.executeUpdate("update consumer_numdata set consumerid= 800365792+count where slno=1 ");
			ps.close();
			ps1.close();
			ps2.close();
			ps3.close();
			cn.close();
		}
		catch(NullPointerException ne)
		{
			System.out.println("Create the object first");
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}

}
