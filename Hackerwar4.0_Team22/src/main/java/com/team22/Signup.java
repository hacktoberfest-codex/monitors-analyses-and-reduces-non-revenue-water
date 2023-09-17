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
	Connection cn=null;
	PreparedStatement ps=null;
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
    		ps=cn.prepareStatement("insert into service_user values(?,?,?,?,?,?,?)");
    		
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
		String field6=request.getParameter("pin");
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
			ps.executeUpdate();
			out.println("<html><body style='background-image: url(./back9.jpg);\r\n"
					+ "background-size: cover; color: blue; font-size: 30px;\r\n"
					+ "text-align: center;'>");
			out.println("<br><br><b>Welcome to DROP BY DROP portal</b>");
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
