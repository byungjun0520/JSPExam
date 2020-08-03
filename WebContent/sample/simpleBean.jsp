<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="sample.*" %>    

<%
   request.setCharacterEncoding("utf-8");
   //String message = request.getParameter("message");

   //SimpleData msg = new SimpleData();
   
  /*  msg.setMessage("우리는 하나"); */
%>    
<jsp:useBean id="msg" class="sample.SimpleData" />    

<jsp:setProperty property="message" name="msg"/>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<font size="5">
메시지 : <jsp:getProperty property="message" name="msg"/>
<%--   <%=message %> --%>
</font>
</body>
</html>