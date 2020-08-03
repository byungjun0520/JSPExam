package sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, 
	HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		Date date = new Date();
		
		out.println("<html>");
		out.println("<body>");
		out.println("My First Servlet Programming !!!!");
		out.println("<br>");
		out.println(date.toString());
		out.println("</body>");
		out.println("</html>");
	}

}
