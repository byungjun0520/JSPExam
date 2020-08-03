<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ page import="java.util.ArrayList" %>

<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>

<%@ page import="java.util.Calendar, java.util.Date " %>
    
<!-- 
 page 지시어
 
   - 페이지 관련 환경을 정의함
  <%-- <%@ page contentType="text/html;  pageEncoding="UTF-8"%> --%>
   - 주로 문자 인코딩, 응답페이지 컨텐츠 타입 등 을 정의함
 -->    

<!-- 

 1. language :  현재는 자바만 지원함

 2. extends : jsp 페이지가 상속 받을 부모 클래스를 지정함

 3. import : import 할 패키지를 명시함

 -->
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<%
   Calendar cal = Calendar.getInstance();
%>

오늘은 <%=cal.get(cal.YEAR) %>년
     <%=cal.get(cal.MONTH)+1 %>월
     <%=cal.get(cal.DATE) %>일
     입니다.

</body>
</html>