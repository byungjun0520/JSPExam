<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> DBCP 연결 </title>
</head>
<body>
<%
   try {
	   InitialContext ctx = new InitialContext();
	   DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mydb");
	   Connection con = ds.getConnection();
	   out.println("오라클 데이터베이스 연결 성공 ");
	   out.close();
   }catch(Exception e) {
	   out.println("오라클 데이터베이스 실패!!!");
   }
%>

</body>
</html>