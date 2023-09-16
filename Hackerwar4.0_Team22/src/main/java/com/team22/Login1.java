package com.team22;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;


/**
 * Servlet implementation class Login
 */
public class Login1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection cn=null;
	PreparedStatement ps=null;
	ServletContext context=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login1() {
    	System.out.println("Login Servlet object is constructed");
    	
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() {
		System.out.println("Login Servlet object is initialized");
		context=getServletContext();
    	String d=context.getInitParameter("driver");
    	String ur=context.getInitParameter("url");
    	String us=context.getInitParameter("username");
    	String pa=context.getInitParameter("password");
    	try 
    	{
    		Class.forName(d);
    		cn=DriverManager.getConnection(ur,us,pa);
    		ps=cn.prepareStatement("select * from consumer_user where consumerid=(?) and password=(?)");
    		
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
		String s1=request.getParameter("user");
		String s2=request.getParameter("pass");
		try
		{
			ps.setString(1,s1);
			ps.setString(2, s2);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				out.println("<html><body style='background-color:springgreen; color: red; font-size: 30px; text-align: center;'>");
				out.println("<br><br><b>Welcome to DROP BY DROP portal</b>");
				out.println("</body></html>");
			}
			else
			{
				out.println("<html><body style='background-color:aquamarine; color: red; font-size: 30px; text-align: center;'>");
				out.println("<br><br><b><i>You are not an authorised User. Check Username and Password.</i></b>");
				out.println("<br><a href='http://localhost:8082/Hackerwar4.0_Team22/login.html'>Go back</a>");
				out.println("</body></html>");
			}
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
