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
    		ps=cn.prepareStatement("select * from service_user where name=(?) and password=(?)");
    		
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
				out.println("<!DOCTYPE html>\r\n"
						+ "<html lang=\"en\">\r\n"
						+ "\r\n"
						+ "<head>\r\n"
						+ "    <meta charset=\"UTF-8\">\r\n"
						+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
						+ "    <title>Document</title>\r\n"
						+ "    <style>\r\n"
						+ "        body {\r\n"
						+ "            background-image: url(./back9.jpg);\r\n"
						+ "            background-size: cover;\r\n"
						+ "        }\r\n"
						+ "\r\n"
						+ "        .first {\r\n"
						+ "            color: blue;\r\n"
						+ "            margin-left: 70px;\r\n"
						+ "            margin-top: 90px;\r\n"
						+ "        }\r\n"
						+ "\r\n"
						+ "        .second {\r\n"
						+ "            color: green;\r\n"
						+ "        }\r\n"
						+ "\r\n"
						+ "        .signinbutn {\r\n"
						+ "            background-color: #04AA6D;\r\n"
						+ "            color: white;\r\n"
						+ "            padding: 14px 20px;\r\n"
						+ "            margin: 8px 0;\r\n"
						+ "            border: none;\r\n"
						+ "            cursor: pointer;\r\n"
						+ "            width: 50%;\r\n"
						+ "            opacity: 0.9;\r\n"
						+ "            /* height: 35px;\r\n"
						+ "    width: 100px px; */\r\n"
						+ "        }\r\n"
						+ "\r\n"
						+ "        button:hover {\r\n"
						+ "            opacity: 1;\r\n"
						+ "        }\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "        /* Float cancel and signup buttons and add an equal width */\r\n"
						+ "        .cancelbtn,\r\n"
						+ "        .signupbtn {\r\n"
						+ "            float: left;\r\n"
						+ "            width: 50%;\r\n"
						+ "        }\r\n"
						+ "    </style>\r\n"
						+ "</head>\r\n"
						+ "\r\n"
						+ "<body>\r\n"
						+ "    <h1 class=\"first\"><b>Welcome to <span class=\"second\">DROP BY DROP</span> data portal</b></h1>\r\n"
						+ "    <a href=\"./admin_dashboard.html\"><input class=\"signinbutn\" type=\"button\" value=\"Admin Dashboard\"></a>\r\n"
						+ "    <a href=\"./layouts-fullscreen.html\"><input class=\"signinbutn\" type=\"button\" value=\"Total Flow and Usage\"></a>\r\n"
			
						+ "</body>\r\n"
						+ "\r\n"
						+ "</html>");
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
