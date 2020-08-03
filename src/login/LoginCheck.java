package login;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		String dbID ="admin";
		String dbPWD ="1234"; 
		
		if(dbID.equals(id) && dbPWD.equals(pwd)) {
			//HttpSession 객체 얻기
			HttpSession session = request.getSession();
			session.setAttribute("user", id);
		}
		
		response.sendRedirect("Login");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
