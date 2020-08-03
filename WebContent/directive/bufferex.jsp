<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page buffer="1kb" autoFlush="true" %>
<!-- 
autoFlush=false로 지정하면 안됨 
이유 : 버퍼가 없는 상태에서는 버퍼에 대한 예외가 발생될 수 없기 때문임
 -->    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<!-- 이부분에서 4Kbyte 이상의 데이터 발생이 됨  -->
<%  for(int i =0; i<1000; i++) { %>
1234
<% } %>

</body>
</html>