package com.st.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckPerson extends HttpServlet{
	
	
	String name=null;
	int age=0;
	PrintWriter pw=null;
	
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	name=req.getParameter("name");
	age=Integer.parseInt(req.getParameter("age"));
	
	pw=res.getWriter();
	res.setContentType("text/html");
	
	if(age<=17){
		pw.write("You are not Adult and Not eligible for Marry");
		
	}
	else if(age>=18 && age<21)
	{
		pw.write("You are adult and If You Female then you are eligible to marry");
	}
	else if(age>=21&&age<=30)
	{
		pw.write("You are Young anf if you are boy then aligible to marry ");
	}
	else if(age>30&&age<=45)
	{
		pw.write("You are middle age person");
		
	}
	else if(age>45&& age<=65)
	{
		pw.write("You are Somewhat Old Person");
		
	}
	else 
	{
		pw.write("You are oldest person");
	}
		
	 pw.println("<a href='PersonForm.html'>home </a>");
	   //close stream 
	  pw.close();
}	//doGet(-,-)
@Override
public  void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    doGet(req,res);
}//doPost(-,-)
	

}//class
