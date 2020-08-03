<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%
    String memberid =(String)session.getAttribute("MEMBERID");
    String name = (String)session.getAttribute("NAME");
 
 %>   
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
MemberId : <%=memberid %><br><br>
Name : <%=name %>


</body>
</html>