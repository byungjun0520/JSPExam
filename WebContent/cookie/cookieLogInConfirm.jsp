<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="color.jsp" %>

<%

  String id="";

  try {
	  
	  Cookie[] cookies = request.getCookies();
	  
	  if(cookies != null) {
		  
		  for(int i = 0; i <cookies.length; i++) {
			  if(cookies[i].getName().equals("memId")) {
				  id = cookies[i].getValue();
			  }
		  }
		  if(id.equals("")) {
			  response.sendRedirect("cookieMemberLogIn.jsp");
		  }
	  }else {
		  response.sendRedirect("cookieMemberLogIn.jsp");
	  }
	}catch(Exception e){}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키를 사용한 회원 인증</title>
<link href="style.css" type="text/css" rel="stylesheet">
</head>
<body bgcolor="<%=bodyback_c %>">
   <form action="cookieLogOut.jsp" method="post">
     <table width="300" border="1" align="center">
       <tr>
          <td align="center" bgcolor="<%=value_c %>">
              <b><%=id %></b>님이 로그인 하셨습니다.
          </td>
       </tr>
       
       <tr>
         <td align="center" bgcolor="<%=value_c %>">
             <input type="submit" value="로그아웃">
         </td>
       </tr>
       
     </table>
   </form>
</body>
</html>