package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.model.BoardVO;
import board.model.ConnUtil;

public class BoardDAO {

	private static BoardDAO instance = null;
	public BoardDAO() {}
	public static BoardDAO getInstance() {
		
		if(instance == null) {
			synchronized (BoardDAO.class) {
				instance = new BoardDAO();
			}
		}
		return instance;
	}
	
	// 게시판 작업의 기능 하나하나를 추가할때 활용할 메소드 정의
	
	
	// 전체 글의 개수를 알아내 주는 메소드 추가
public int getArticleCount() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		
		try {
			con = ConnUtil.getConnection();
			String sql ="select count(*) from board";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
	
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(rs != null) try { rs.close(); }catch(SQLException ss) {}
			if(pstmt != null) try { pstmt.close(); }catch(SQLException ss) {}
			if(con != null) try { con.close(); }catch(SQLException ss) {}
		}
		return x;
	}
	
	// 글 목록을 얻어와서 list형태로 리턴해 줄 메소드 추가
     public List<BoardVO> getArticles(int start, int end) {// 수정1
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	List<BoardVO> articleList = null;
	
	try {
		con = ConnUtil.getConnection();
		
		// 수정2
		// select * from board order by num desc
		pstmt =con.prepareStatement(
		"select * from (select rownum rnum, num, writer, email, "
		+ "subject, pass, regdate, readcount, ref, step, depth, "
		+ "content, ip from (select * from board order by ref desc, "
		+ "step asc)) where rnum >= ? and rnum <= ?");
		
		// 수정 3
		pstmt.setInt(1, start);
		pstmt.setInt(2, end);
		
		rs = pstmt.executeQuery();
		if(rs.next()) {
			articleList = new ArrayList<BoardVO>(5);// 수정 4
			do {
			    BoardVO article = new BoardVO();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setEmail(rs.getString("email"));
				article.setSubject(rs.getString("subject"));
				article.setPass(rs.getString("pass"));
				article.setRegdate(rs.getTimestamp("regdate"));
				article.setRef(rs.getInt("ref"));
				article.setStep(rs.getInt("step"));
				article.setDepth(rs.getInt("depth"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
				articleList.add(article);
			}while(rs.next());
		}
	}catch(Exception ex) {
		ex.printStackTrace();
	}finally {
		if(rs != null) try { rs.close(); }catch(SQLException ss) {}
		if(pstmt != null) try { pstmt.close(); }catch(SQLException ss) {}
		if(con != null) try { con.close(); }catch(SQLException ss) {}
	}
	
	return articleList;
}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
