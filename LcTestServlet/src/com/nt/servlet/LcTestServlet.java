package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.util.Date;

public class LcTestServlet extends HttpServlet{
	static{
		
		System.out.println("LcTestServlet:Static Block");
	}
	public LcTestServlet()
	{
		System.out.println("LcTestServlet:0-param Contructor");
		
	}

public void init(ServletConfig cg)
	{
		System.out.println("LcTestServlet:init()");
	}
public void service(ServletRequest req, ServletResponse res)throws ServletException, IOException{
	System.out.println("LcTestServlet:Service(-,-)");
	
	
//Generating Setting 
PrintWriter pw=res.getWriter();
res.setContentType("text/html");
//generate Response 
pw.println("<h1>Date & Time "+new Date());

pw.close();
}//service
public void destroy()
{
	System.out.println("LcTestServlet:Destroy() Method called");
}

}

