package sample;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

//@WebServlet("/Source")
public class Source extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("Source Start...............");
		
		// 페이지 이동
		// 1. forward 방식으로 이동
		
		  RequestDispatcher view = request.getRequestDispatcher("Destination");
		  // 이동할 페이지
		  view.forward(request, response);
		 
		// 2. redirect 방식으로 이동
		// response.sendRedirect("Destination");
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}