<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

</head>
<%-- 

    선언문
   - JSP 페이지에 삽입된 자바 코드를 통해서 멤버변수나 멤버 메소드를 선언할때 사용함
         형식 : <%! 선언문 %> 

 스크립트
   - JSP 페이지에서 작성된 자바 코드를 지원함
         형식 : <%  자바코드 %>   
 
 표현식
   - JSP 페이지네애서 직접 클라이언트로 출력된 내용을 표시할때 사용
         형식 : <%=  표현식 => 

--%>

<body>
   <h2>JSP Script</h2>
     
   
     <%
        String scriptlet = "스크립트릿 입니다.";
        String comment= "주석문 입니다.";
        out.println("내장객체를 이용한 출력 :"+declation +"<br><br>");
     %>   

      선언문(변수) 출력 : <%=declation %><br> 
     선언문 (메소드) 출력 : <%=declationMethod() %><br>
      스크립트릿 출력 : <%=scriptlet %><br>

     <%!
        // 변수 선언
        String declation ="선언문 입니다.";
      %>
   
      <%!
        // 메소드 선언
        public String declationMethod() {
    	  return declation;
      }
      
      %>
</body>
</html>