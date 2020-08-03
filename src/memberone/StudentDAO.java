package memberone;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;

public class StudentDAO {

	// 디비 연결함수
	private Connection getConnection() {

		Connection con = null;
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mydb");
			con = ds.getConnection();
		} catch (Exception se) {
			System.err.println("Connection 생성 실패!!!");
		}

		return con;
	}

	// 아이디 체크함수
	public boolean idCheck(String id) {
		boolean result = true;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();

			pstmt = con.prepareStatement("select * from student where id =?");

			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (!rs.next())
				result = false;
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException se) {
				}
			
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException se) {
				}
			
			if (con != null)
				try {
					con.close();
				} catch (SQLException se) {
				}
			
		}

		return result;
	}

	// 우편번호 디비검색해서 Vector 저장할 메소드
	public Vector<ZipCodeVO> zipcodeRead(String dong) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector<ZipCodeVO> vecList = new Vector<ZipCodeVO>();

		try {

			con = getConnection();

			String strQuery = "select * from zipcode where dong like '" + dong + "%'";

			pstmt = con.prepareStatement(strQuery);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				ZipCodeVO tempZipcode = new ZipCodeVO();
				tempZipcode.setZipcode(rs.getString("zipcode"));
				tempZipcode.setSido(rs.getString("sido"));
				tempZipcode.setGugun(rs.getString("gugun"));
				tempZipcode.setDong(rs.getString("dong"));
				tempZipcode.setRi(rs.getString("ri"));
				tempZipcode.setBunji(rs.getString("bunji"));

				vecList.addElement(tempZipcode);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException se) {
				}
			
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException se) {
				}
			
			if (con != null)
				try {
					con.close();
				} catch (SQLException se) {
				}
		  }
		return vecList;
	}
	
	// 데이터베이스에 회원 데이터를 넣기 위한 메소드 추가
	public boolean memberInsert(StudentVO vo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		
		try 
		{
			con = getConnection();
			
			String strQuery ="insert into student values(?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(strQuery);
			
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
			
			int count = pstmt.executeUpdate();
			if(count > 0) flag = true;
			
		}catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException se) {
				}
			
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException se) {
				}
			
			if (con != null)
				try {
					con.close();
				} catch (SQLException se) {
				}
		  }
		return flag;	
	}
	
	// db에서 id/password를 비교하여 그 결과를 정수형으로 리턴함
	// 1:로그인 성공, 0: 비밀번호 오류, -1:아이디 없음
	public int loginCheck(String id, String pass) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int check = -1;
		
		try {
			
			con=getConnection();
			
	String strQuery ="select pass from student where id=?";
	pstmt = con.prepareStatement(strQuery);
	pstmt.setString(1, id);
	rs =pstmt.executeQuery();
	if(rs.next()) {
		String dbPass = rs.getString("pass");
		if(pass.equals(dbPass)) check = 1;
		else check = 0;
	 }
	}catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException se) {
				}
			
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException se) {
				}
			
			if (con != null)
				try {
					con.close();
				} catch (SQLException se) {
				}
		  }
		return check;
	}
	
	// 아이디를 가지고 회원정보를 가져올 메소드 구현
		
  public StudentVO getMember(String id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentVO vo = null;
		
		//String strQuery="select * from student where id=?";
		
		try {
	con = getConnection();
	pstmt = con.prepareStatement("select * from student where id=?");
	
	pstmt.setString(1, id);
	
	rs = pstmt.executeQuery();
	
	if(rs.next()) {// 해당 아이디에 회원이 존재하면
		vo = new StudentVO();
		
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
	}catch (Exception se) {
		se.printStackTrace();
	} finally {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException se) {
			}
		
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException se) {
			}
		
		if (con != null)
			try {
				con.close();
			} catch (SQLException se) {
			}
	  }
	return vo;
	}
	
	// 정보 수정 버튼을 누르면 데이터베이스에 update를 수행해야함
    // 정보수정을 처리할 메소드 구현
  
    public void updateMember(StudentVO vo) {
    	
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	
    	try {
    		
    		con =getConnection();
    		
    	String sql ="update student set pass=?, phone1=?, phone2=?, "
    				+ "phone3=?, email=?, zipcode=?, address1=?,"
    				+ " address2=? where id=?";
    	   		
    		pstmt = con.prepareStatement(sql);
    		
    		pstmt.setString(1, vo.getPass());
    		pstmt.setString(2, vo.getPhone1());
    		pstmt.setString(3, vo.getPhone2());
    		pstmt.setString(4, vo.getPhone3());
    		pstmt.setString(5, vo.getEmail());
    		pstmt.setString(6, vo.getZipcode());
    		pstmt.setString(7, vo.getAddress1());
    		pstmt.setString(8, vo.getAddress2());
    		pstmt.setString(9, vo.getId());
    		
    		pstmt.executeUpdate();
    		
    	}catch (Exception se) {
    		se.printStackTrace();
    	} finally {
    		  		
    		if (pstmt != null)
    			try {
    				pstmt.close();
    			} catch (SQLException se) {
    			}
    		
    		if (con != null)
    			try {
    				con.close();
    			} catch (SQLException se) {
    			}
    	  }
   
    }
	
    
	// 삭제가 성공하면 1을 리턴 실패하면 0을 리턴
    // 정수값을 가지고 결과를 처리하는 메소드 구현
    // 탈퇴버튼이 클릭되면 데이터베이스에서 회원 데이터 삭제처리되는 메소드
    
    public int deleteMember(String id, String pass) {
    	Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbPass ="";
		int result = -1;
		
    	
    	try {
    		con = getConnection();
    		String sql="select pass from student where id=?";
    		pstmt = con.prepareStatement(sql);
    		pstmt.setString(1, id);
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			
    			dbPass = rs.getString("pass");
    			
    			if(dbPass.equals(pass)) {
    				
    				pstmt = con.prepareStatement(
    						"delete from student where id=?");
    				
    				pstmt.setString(1, id);
    				pstmt.executeUpdate();
    				result = 1;// 회원 탈퇴 성공
    				
    			}else {// 비밀번호 오류
    				result = 0;
    			}
    		}
    	}catch (Exception se) {
			se.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException se) {
				}
			
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException se) {
				}
			
			if (con != null)
				try {
					con.close();
				} catch (SQLException se) {
				}
		  }
    	
    	return result;
    }
    
   
    
    
    
    
    
    
    
}
