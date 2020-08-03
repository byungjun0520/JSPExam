<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    
     session.setMaxInactiveInterval(10*60);
    
    session.setAttribute("MEMBERID", "stdio");
    session.setAttribute("NAME", "홍길동");
    
    
    %>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
세션에 정보를 저장하였습니다.
</body>
</html>