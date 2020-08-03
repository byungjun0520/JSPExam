package com.test;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/Hello")
//@WebServlet(name = "MyHello", urlPatterns = { "/aaa", "/bbb", "/ccc" })
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   // 서비스 호출 메소드
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Hello servlet 요청");

	}

	
	// 서블릿이 처음으로 생성할때 단 한번만 호출되는 메소드
	// 초기작업때 주로 사용하는 메소드
	@Override
	public void init() throws ServletException {

		System.out.println("init() 메소드 호출");
	}

	
	// 웹 컨터이너에서 제거될때 호출되는 메소드
	// init() 메소드에서 구현한 초기화 작업을 반납처리할때 호출되는 메소드
	@Override
	public void destroy() {
		System.out.println("destroy() 메소드 호출");

	}
	
	@PostConstruct
    public void initMethod() {
    	System.out.println("initMethod()");
    }
	
	@PreDestroy
    public void clean() {
    	System.out.println("clean()");
    }

}
