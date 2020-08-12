<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="mvcMem.model.*" %>    
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 확인</title>
<link href="style.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="script.js"></script>
</head>
<body bgcolor="#FFFFCC">
<c:set var="flag" value="${flag }" />
<br><br>
<div align="center">
    <c:choose>
            <c:when test="${flag }">
            <b>회원 가입을 진심으로 축하드립니다.</b><br>
            <a href='member.mdo?cmd=login'>로그인</a>
            </c:when>
            
            <c:otherwise>
            <b>다시 입력하여 주십시요.</b><br>
            <a href='member.mdo?cmd=regForm'>다시입력</a>
            </c:otherwise>
    </c:choose>
 </div>
</body>
</html>