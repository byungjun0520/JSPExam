package sample;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

//@WebServlet("/Destination")
public class Destination extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		try {
	
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Destination</title>");
			out.println("</head>");
			out.println("<body>");
			
			out.println("<h1> Destination Servlet 입니다.</h1>");
			
			out.println("</body>");
			out.println("</html>");
		}finally {
			out.close();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
