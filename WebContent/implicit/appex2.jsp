<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서버 정보 출력</title>
</head>
<body>
서버정보 : <%=application.getServerInfo() %><br><br>
서블릿 규약 메이저 버전 : <!-- 정수 부분만 리턴 : 3.1 -->
<%=application.getMajorVersion() %><br><br>
서블릿 규약 마이너 버전 : <!-- 소수점 부분만 리턴 -->
<%=application.getMinorVersion() %>


</body>
</html>