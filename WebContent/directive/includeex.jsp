<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<h2> include 지시어 </h2>
<%
     String name="Gil Dong Hong";
%>
<%@ include file="top.jsp" %>
top.jsp 내용 포함
<%@ include file="bottom.jsp" %>

</body>
</html>