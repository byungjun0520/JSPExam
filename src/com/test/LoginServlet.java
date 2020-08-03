package com.test;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


//@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<body>");
		out.println("아 이 디 :"+userid+"<br>");
		out.println("비밀번호:"+passwd+"<br>");
		out.println("</body>");
		out.println("</html>");
		
	}

}








