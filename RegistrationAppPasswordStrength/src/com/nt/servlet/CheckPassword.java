package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckPassword extends HttpServlet{
	
	String pswd=null;
	boolean isPasswordNumeric=false;
	int insideFor;
	PrintWriter pw=null;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		pw=res.getWriter();
		res.setContentType("text/html");
		//Read Form Data
		pswd=req.getParameter("pswd");
		int len=pswd.length();
		if(len==0||pswd==null)
		{
			pw.println("Please Enter Password");
			pw.println("<a href='CheckPassword.html'>OK</a>");
		}
		else if(len<6)
		{
			pw.println("Please Enter Password atleast 6 Character including Uppercase ,Special Character,Lowercase and Number ");
			pw.println("<a href='CheckPassword.html'>OK</a>");
		}
		else if(len>=6)
		{insideFor=1;
			for(int i=0;i<len;i++)
		{
			if(!Character.isUpperCase(pswd.charAt(i))&&!Character.isLowerCase(pswd.charAt(i)))
{isPasswordNumeric=true;

break;
	
}//if
			
		}//for
		}//if
		
			if(isPasswordNumeric==false & insideFor==1 )
			{
				pw.println("Password Weak");
				pw.println("<a href='CheckPassword.html'>OK</a>");
			}//if
			if(isPasswordNumeric==true & insideFor==1 ){
				pw.println("Password Strong");
				pw.println("<a href='CheckPassword.html'>OK</a>");
			}//if
	pw.close();
		}//doGet(-,-)
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	doGet(req,res);
	}//doPost(-,-)
}///Class


