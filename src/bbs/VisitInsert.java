package bbs;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

//@WebServlet("/VisitInsert")
public class VisitInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	request.setCharacterEncoding("utf-8");
	// 클라이언트가 http(write.html)요청으로 전송한 값을 읽어옴
	String writer = request.getParameter("writer");
	String memo = request.getParameter("memo");
	System.out.println("작성자 :"+writer);
	System.out.println("내용 :"+memo);
	
	// 가져온 파라미터 값을 데이터 베이스에 저장
	StringBuffer sql = new StringBuffer();
	sql.append("insert into visit(no, writer, memo, regdate) ");
	sql.append("values(visit_seq.nextval, ?, ?, sysdate)");
	
	Connection con = null;
	PreparedStatement pstmt = null;
	
	try {
		// Class.forName
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//DriverManager.getConnection()
		con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:orcl",
		        "scott", "tiger");
		
		pstmt = con.prepareStatement(sql.toString());
		pstmt.setString(1, writer);
		pstmt.setString(2, memo);
		pstmt.executeUpdate();
	}
	catch(SQLException se) {
		se.printStackTrace();
	}
	catch(ClassNotFoundException e) {
		e.printStackTrace();
	}
	finally {
		try { if(pstmt != null) pstmt.close();}catch(SQLException ss) {}
		try { if(con != null) con.close();}catch(SQLException ss) {}
	}
	
	response.sendRedirect("VisitList");
	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
