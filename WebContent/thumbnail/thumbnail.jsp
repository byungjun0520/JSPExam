<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 썸네일 이미지를 만들기 위해 JAI 클래스 및 그래픽 관련된 클래스를 임포트 함 -->
<%@ page import="java.awt.Graphics2D" %>
<%@ page import="java.awt.image.renderable.ParameterBlock" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="javax.media.jai.JAI" %>
<%@ page import="javax.media.jai.RenderedOp" %>
<%@ page import="javax.imageio.ImageIO" %>
<!-- 썸네일 파일을 만들기 전에 썸네일 이미지로 변환할 이미지를 업로드해야하므로 업로드 
        관련 클래스 임포트-->
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>

<%
    String imagePath = request.getRealPath("upload");
    // 실제로 업로드 될 폴더의 경로 설정
    int size =1*1024*1024;
    // 파일 크기 설정
    String filename="";
    
    try {
    	MultipartRequest multi = new MultipartRequest(request,
    			imagePath, size, "utf-8", new DefaultFileRenamePolicy());
    	// 썸네일 이미지로 변환할 이미지 업로드
    	
    	Enumeration files = multi.getFileNames();
    	String file = (String)files.nextElement();
    	filename=multi.getFilesystemName(file);
        // 업로드 된 이미지 파일을 이름을 얻음
       }catch(Exception e) {
    	   e.printStackTrace();
       }
       
       ParameterBlock pb = new ParameterBlock();
       pb.add(imagePath+"/"+filename);
       RenderedOp rOp = JAI.create("fileload", pb);
       // 파라미터 블럭 클래스에 변환할 이미지를 저장하고 그 이미지를 불러옴
       // 파라미터 블럭 클래스를 통해서만 이미지를 담을 수 있음 
       // 파라미터 블럭에 업로드 된 이미지를 담고 JAI가 제공하는 코덱을 사용한다는 옵션을 사용함
       
       BufferedImage bi = rOp.getAsBufferedImage();
       //불러온 이미지를 버퍼이미지를 생성해서 저장함
       BufferedImage thumb = 
    		   new BufferedImage(100,100, BufferedImage.TYPE_INT_RGB);
        // 썸네일이라는 이미지 버퍼를 생성후 버퍼의 사이즈를 100 * 100 으로 설정
       Graphics2D g = thumb.createGraphics();
        g.drawImage(bi, 0,0, 100, 100, null);
       // 이미지 버퍼에 원본 이미지를 정해진 버퍼 사이즈로 맞추어 드로우 함
       
       File file = new File(imagePath+"/sm_"+filename);
       ImageIO.write(thumb, "jpg", file);      
       //  출력 위치와 파일 이름 설정후 썸네일 이미지를 생성함
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
원본 이미지 <br>
<img alt="원본" src="/JSPExam/upload/<%=filename%>"><p>
썸네일 이미지 <br>
<img alt="썸네일" src="/JSPExam/upload/sm_<%=filename%>">
</body>
</html>