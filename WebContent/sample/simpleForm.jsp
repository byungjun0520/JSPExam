<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!--   자바빈즈 : 자바 클래스 중에서 자바빈즈 규약에 맞게 작성된 클래스를 의미함
                 멤버 변수와 getter/setter 메소드로 이루어져 있음
                값을 저장하는 Value Object로 활용됨 -->
       <%--  <jsp:useBean> --> 
       <!-- 액션 태그를 이용함 -->        
 <!--  형식 : -->
  <%--  <jsp:useBean id="빈 이름" scope="범위" class="빈의 저장위치" /> --%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

<h1>자바 빈즈</h1>
<hr color="red"><br><br>
<form action="simpleBean.jsp" method="post">
    메시지 : <input type="text" name="message">
         <input type="submit" value="전송">
</form>
</body>
</html>