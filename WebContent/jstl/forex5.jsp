<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<!-- var:token, items=문자열, delims=구분자  -->
<c:forTokens var="car" 
items="Sprinter Trueno AE86,RX-7  Savanna ECS3S,Lancer Evolution III, RX-7 Efini DF3S" 
delims=",">

자동차이름: <c:out value="${car}" /><br>

</c:forTokens>








</body>
</html>