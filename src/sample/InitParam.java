package sample;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


//@WebServlet("/InitParam")
public class InitParam extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String company;
	private String manager;
	private String tel;
	private String email;
	
	/*  ServletConfig 
	 *   - ServletConfig 객체는 Container가 서블릿을 생성할때 생성되는 객체임
	 *   - DD(web.xml)를 읽어서 이름/값의  쌍으로 된 초기화 파라미터를 읽어서 저장함
	 *   - ServletConfig 객체는 Servlet 객체당 한개씩 생성함
	 *   - Servlet 에서는 getServletConfig()를 이용해서 servlet과 관련된
	 *     ServletConfig 객체를 얻을 수 있음
	 *     
	 *   ServletContext  
	 *     - ServletContext 객체는 web application 당 하나씩 생성됨
	 *     - web application 전체에서 참조할 수 있는 초기 파라미터를 저장할 수 있음
	 *     - Servlet에서는 getServletContext()를 이용해서 ServletContext
	 *       객체를 얻을 수 있음
	 */
	
	
	@Override
	public void init() throws ServletException {
	System.out.println("초기화 메소드 수행....");
	// ServletContext의 초기 파라미터 값 읽기
	company = getServletContext().getInitParameter("company");
	manager = getServletContext().getInitParameter("manager");
	
	// ServletConfig의 초기 파라미터 값 읽기
	tel = getServletConfig().getInitParameter("tel");
	email = getServletConfig().getInitParameter("email");

	}
	
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		try {
			response.setContentType("text/html;charset=utf-8");
			out.println("<html>");
			out.println("<body>");
			out.println("<li> 회사명 :"+ company+"</li>");
			out.println("<li> 담당자 :"+ manager+"</li>");
			out.println("<li> 연락처 :"+ tel+"</li>");
			out.println("<li> 이메일 :"+ email+"</li>");
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
