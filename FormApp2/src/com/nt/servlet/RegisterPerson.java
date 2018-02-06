package com.nt.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterPerson extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	PrintWriter pw=null;
	String name=null;
	int age=0;
	String gen=null;
	String  ms=null;
	String qlyf=null;
	String course=null;
	String addrs=null;
	String hobbie=null;
	///////General Settings
	
	pw=res.getWriter();
	res.setContentType("text/html");
	name=req.getParameter("pname");
	age=Integer.parseInt(req.getParameter("page"));
	gen=req.getParameter("gen");
	ms=req.getParameter("ms");
	qlyf=req.getParameter("pquly");
	
	
	
}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
	

}
