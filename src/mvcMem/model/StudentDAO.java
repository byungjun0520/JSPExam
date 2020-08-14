package mvcMem.model;

// 데이터베이스 연동을 담당 해줄 DAO 클래스
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;


public class StudentDAO {

	private static StudentDAO instance = null;
	
	private StudentDAO() {}
	
	public static StudentDAO getInstance() {
		
		if(instance == null) {
			synchronized (StudentDAO.class) {
				instance = new StudentDAO();
			}
		}
		
		return instance;
		
	}
	
	
	private Connection getConnection() {
		
		Connection con = null;
		
		try {
			
			Context init  =  new InitialContext();
			DataSource ds =(DataSource) init.lookup("java:comp/env/jdbc/mydb");
			con = ds.getConnection();
			
		}catch(SQLException ss) {
			System.out.println("연결 실패");
			ss.printStackTrace();
		}catch(NamingException e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		}
		return con;

	}
	
	public boolean idCheck(String id) {
		
        boolean result = true;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = getConnection();
			pstmt =con.prepareStatement(
					"select * from student where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(!rs.next()) result =false;
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		finally {
			if(rs != null) try { rs.close();}catch(SQLException ss) {}
			if(pstmt != null) try { pstmt.close();}catch(SQLException ss) {}
			if(con != null) try { con.close();}catch(SQLException ss) {}
		}
	
		return result;
		
	}
	
	public Vector<ZipCodeVO> zipcodeRead(String dong) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		 Vector<ZipCodeVO> vecList = new  Vector<ZipCodeVO>();
		 
		 try {
			 con =getConnection();
			 
			 String strQuery =
					 "select * from zipcode where dong like '"+dong+"%'";
			 
			 pstmt = con.prepareStatement(strQuery);
			 rs =pstmt.executeQuery();
			 
			 while(rs.next()) {
				 
				 ZipCodeVO tempZipcode = new ZipCodeVO();
				 
				 tempZipcode.setZipcode(rs.getString("zipcode"));
				 tempZipcode.setSido(rs.getString("sido"));
				 tempZipcode.setGugun(rs.getString("gugun"));
				 tempZipcode.setDong(rs.getString("dong"));
				 tempZipcode.setRi(rs.getString("ri"));
				 tempZipcode.setBunji(rs.getString("bunji"));
				 
				 vecList.addElement(tempZipcode);
				 
			 }
		
		 }catch(SQLException se) {
				se.printStackTrace();
			}
			finally {
				if(rs != null) try { rs.close();}catch(SQLException ss) {}
				if(pstmt != null) try { pstmt.close();}catch(SQLException ss) {}
				if(con != null) try { con.close();}catch(SQLException ss) {}
			}
		 
		 return vecList;

	}
	
	public boolean memberInsert(StudentVo vo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false; 
		
		try {
			
			con =getConnection();
						
			String strQuery =
					"insert into student values(?,?,?,?,?,?,?,?,?,?)";
			
			pstmt =con.prepareStatement(strQuery);
			
			
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPass());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getPhone1());
			pstmt.setString(5, vo.getPhone2());
			pstmt.setString(6, vo.getPhone3());
			pstmt.setString(7, vo.getEmail());
			pstmt.setString(8, vo.getZipcode());
			pstmt.setString(9, vo.getAddress1());
			pstmt.setString(10, vo.getAddress2());
			
			if(pstmt.executeUpdate() >0 )
				 result = true;
			
		}catch(Exception ee) {
			System.out.println("Exception "+ee);
		}finally {
			if(rs != null) try { rs.close();}catch(SQLException ss) {}
			if(pstmt != null) try { pstmt.close();}catch(SQLException ss) {}
			if(con != null) try { con.close();}catch(SQLException ss) {}
		}
		
		return result;
	
	}
	
     public boolean updateMemebr(StudentVo vo) {
		
    	 boolean result = false;
    	 Connection con = null;
 		 PreparedStatement pstmt = null;
 		// ResultSet rs = null;
 		
 		try
 		{
 			con = getConnection();
 			
 			pstmt = con.prepareStatement(
 					"update student set pass=?, phone1=?, phone2=?, phone3=?, "
 					+ "email=?, zipcode=?, address1=?,  address2=?  where id=?");
 			
 			pstmt.setString(1, vo.getPass());
 			pstmt.setString(2, vo.getPhone1());
 			pstmt.setString(3, vo.getPhone2());
 			pstmt.setString(4, vo.getPhone3());
 			pstmt.setString(5, vo.getEmail());
 			pstmt.setString(6, vo.getZipcode());
 			pstmt.setString(7, vo.getAddress1());
 			pstmt.setString(8, vo.getAddress2());
 			pstmt.setString(9, vo.getId());
 			
 			
 			if(pstmt.executeUpdate() > 0)
 				result = true;
 			
 		}catch(SQLException e) {
 			e.printStackTrace();
 		}finally {
 			if(pstmt != null) try { pstmt.close();}catch(SQLException ss) {}
 			if(con != null) try { con.close();}catch(SQLException ss) {}
 		}
 		
    	 return result;
   }
	
     public int loginCheck(String id, String pass) {
    		
    	    Connection con = null;
    		PreparedStatement pstmt = null;
    		ResultSet rs = null;
    		
    		int result = -1;
    		
    		try {
    			
    			con = getConnection();
    			
    			String strQuery ="select pass from student where id=? ";
    			
    			pstmt = con.prepareStatement(strQuery);
    			
    			pstmt.setString(1, id);
    			
    			rs = pstmt.executeQuery();
    			
    			
    			if( rs.next()) {
    				
    				String dbPass = rs.getString("pass");
    				   				
    				if(pass.equals(dbPass)) result = 1;
    				    			
    				else result =0;
    			
    			}
    				
    		}catch(Exception ee) {
    			System.out.println("Exception :"+ee);
    		}finally {
    			if(rs != null) try { rs.close();}catch(SQLException ss) {}
    			if(pstmt != null) try { pstmt.close();}catch(SQLException ss) {}
    			if(con != null) try { con.close();}catch(SQLException ss) {}
    		}
    		
    		return result;
     }
	
     public int deleteMember(String id, String pass) {
    		
    	   Connection con = null;
    		PreparedStatement pstmt = null;
    		ResultSet rs = null;
    		
    		String dbPass ="";
    		
    		int result = 0;
    		
    		try {
    			con = getConnection();
    			pstmt = con.prepareStatement(
    					"select pass from student where id=?");
    			pstmt.setString(1, id);
    			
    			rs =pstmt.executeQuery();
    			
    			if(rs.next()) {
    				
    				dbPass = rs.getString("pass");
    				
    				if(dbPass.equals(pass)) {
    				
    					pstmt =con.prepareStatement(
    							"delete from student where id =?");
    					
    					pstmt.setString(1, id);
    					pstmt.executeUpdate();
    					
    					result = 1; 
    				
    				
    				}else {
    				
    					result = 0;
    				}
    			}
    		}catch(Exception e) {
    			e.printStackTrace();
    		}finally {
    			if(rs != null) try { rs.close();}catch(SQLException ss) {}
    			if(pstmt != null) try { pstmt.close();}catch(SQLException ss) {}
    			if(con != null) try { con.close();}catch(SQLException ss) {}
    		}
    		
    		return result;
     }
     
     public StudentVo getMember(String id) {
    	 Connection con = null;
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		StudentVo vo = null;
 		
 		try {
 			
 			con = getConnection();
 			
 			pstmt = con.prepareStatement(
 					"select * from student where id=?");
 			
 			pstmt.setString(1, id);
 			rs = pstmt.executeQuery();
 			
 			if(rs.next()) {
 				
 				
 				vo = new StudentVo();
 				
 				vo.setId(rs.getString("id"));
 				vo.setPass(rs.getString("pass"));
 				vo.setName(rs.getString("name"));
 				vo.setPhone1(rs.getString("phone1"));
 				vo.setPhone2(rs.getString("phone2"));
 				vo.setPhone3(rs.getString("phone3"));
 				vo.setEmail(rs.getString("email"));
 				vo.setZipcode(rs.getString("zipcode"));
 				vo.setAddress1(rs.getString("address1"));
 				vo.setAddress2(rs.getString("address2"));
 			}
 		}catch(Exception e) {
 			e.printStackTrace();
 		}
 		
 		finally {
 			if(rs != null) try { rs.close();}catch(SQLException ss) {}
 			if(pstmt != null) try { pstmt.close();}catch(SQLException ss) {}
 			if(con != null) try { con.close();}catch(SQLException ss) {}
 		}
 		
 		return vo;
     }
  
}