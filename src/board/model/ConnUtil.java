package board.model;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

// 데이터베이스 커넥션을 얻어오는 유틸리티 클래스
public class ConnUtil {
	
	private static DataSource ds;
	
	static {
		try {
			InitialContext ctx = new InitialContext();
			ds =(DataSource)ctx.lookup("java:comp/env/jdbc/mydb");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

}
