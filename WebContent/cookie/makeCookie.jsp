<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.net.URLEncoder" %>    
    
<%
/* 쿠키 생성 */

Cookie cookie = 
new Cookie("name", URLEncoder.encode("홍길동", "UTF-8"));

response.addCookie(cookie);

%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키 생성</title>
</head>
<body>
<%=cookie.getName() %> <br>
쿠키값="<%=cookie.getValue() %>"
</body>
</html>



