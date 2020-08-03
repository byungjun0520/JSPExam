package logon;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class LogonDBBean {

	private static LogonDBBean instance = new LogonDBBean();
	
	public LogonDBBean() {
	
	}
	
	public static LogonDBBean getInstance() {
		return instance;
	}
	
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx =(Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/mydb");
		return ds.getConnection();
	}
	
	public int userCheck(String id, String passwd) throws Exception {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd="";
		int x = -1;
		
		try {
			
			con = getConnection();
			pstmt = con.prepareStatement(
			"select passwd from tempmember where id=?");
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				dbpasswd = rs.getString("passwd");
				if(dbpasswd.equals(passwd))
					x =1; // 회원인증 성공
				else 
					x =0;// 비밀번호 오류
			}else 
				x = -1;// 아이디 없음
	     }catch(Exception ex) {
	    	 ex.printStackTrace();
	     }finally {
	     if(rs != null) try { rs.close(); }catch(SQLException ss) {}
		 if(pstmt != null) try { pstmt.close(); }catch(SQLException ss) {}
		 if(con != null) try { con.close(); }catch(SQLException ss) {}
	     }
		return x;
	}
	
}
