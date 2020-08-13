package board.controller;

import java.util.*;
import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board.action.CommandAction;

public class ControllerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;


	// 명령어와 명령어처리 클래스를 쌍으로 저장
	private Map<String, Object> commandMap = 
			new HashMap<String, Object>();
	
	// 명령어와 명령어처리클래스가 매핑되어 있는 properties파일을 읽어서
	// Map 객체인 commandMap에  저장
	
	public void init(ServletConfig config) throws ServletException {
	// web.xml에서 propertyConfig에 해당하는 init-param 의 값을 읽어옴
		String props = config.getInitParameter("propertyConfig");
		
		// 명령어와 명령어처리클래스의 매핑정보를 저장할 Properties 객체를 생성함
		Properties pr = new Properties();
		FileInputStream f = null;
		
		String path = config.getServletContext().getRealPath("/WEB-INF");
		
		try {
			// CommandPro.properties 파일의 내용을 읽어옴
			f = new FileInputStream(new File(path, props));
			
			// CommandPro.properties 파일의 정보를 Properties 객체에 저장함
			pr.load(f);
			
		}catch(IOException e) {
			throw new ServletException(e);
		}finally {
			if(f != null) try {f.close();}catch(IOException ee) {}
		}
		
		//Iterator 객체는 Enumeration 객체를 확장시킨 객체이다.
		Iterator<Object> keyIter = pr.keySet().iterator();
		
		// 객체를 하나씩 꺼내서 그 객체명으로 Properties 객체에 저장된 객체에 접근함
		while(keyIter.hasNext()) {
			
			String command = (String)keyIter.next();
			String className = pr.getProperty(command);
			
			try {
				// 해당 문자열을 클래스로 만듬
				Class commandClass = Class.forName(className);
				
				// 해당 클래스의 객체를 생성
				Object commandInstance = commandClass.newInstance();
				
				//Map 객체인 commandMap에 객체 저장
				commandMap.put(command, commandInstance);
			}catch(ClassNotFoundException e) {
				throw new ServletException(e);
			}catch(InstantiationException e) {
				throw new ServletException(e);
			}catch(IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request,response);
	}
	
	// 사용자의 요청을 분석해서 해당 작업을 처리하는 메소드
	protected void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String view = null;
		CommandAction com = null;
		
		try {
			String command=request.getRequestURI();
			
			if(command.indexOf(request.getContextPath()) == 0) {
				command = command.substring(request.getContextPath().length());
			}
			
			com = (CommandAction)commandMap.get(command);
			view = com.requestPro(request, response);
		}catch(Throwable e) {
			throw new ServletException(e);
		}
		
		RequestDispatcher dispatcher =
				request.getRequestDispatcher(view);
		
		dispatcher.forward(request, response);
		
	}

}
