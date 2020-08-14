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

 	//--------------------------------------------------------
 	// 게시판에 작성된 글을 데이터베이스에  저장하는 메소드 구현
 	public void insertArticle(BoardVO article) {
 		
 		Connection con = null;
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		
 		int num = article.getNum();
 		int ref = article.getRef();
 		int step = article.getStep();
 		int depth= article.getDepth();
 		
 		int number = 0;
 		
 		String sql ="";
 		
 		try {
 			
 			con = ConnUtil.getConnection();
 			pstmt = con.prepareStatement("select max(num) from board");
 			rs = pstmt.executeQuery();
 			
 			if(rs.next()) number = rs.getInt(1)+1;
 			else number =1;
 			
 			if(num != 0) {// 답변 글일 경우
 			sql ="update board set step=step+1 where ref=? and step > ?";	
 			pstmt =con.prepareStatement(sql);	
 		    pstmt.setInt(1, ref);		
 			pstmt.setInt(2, step);
 			pstmt.executeUpdate();
 			step=step+1;
 			depth=depth+1;
 			}else {// 새글일 경우
 				ref = number;
 				step=0;
 				depth =0;
 			}
 			// 새글을 추가하는 쿼리 작성
 			sql ="insert into board(num, writer, email, subject,"
 					+ " pass, regdate, ref, step, depth, content, ip) "
 					+ "values(board_seq.nextval, ?,?,?,?,?,?,?,?,?,?)";
 			
 			pstmt = con.prepareStatement(sql);
 			pstmt.setString(1, article.getWriter());
 			pstmt.setString(2, article.getEmail());
 			pstmt.setString(3, article.getSubject());
 			pstmt.setString(4, article.getPass());
 			pstmt.setTimestamp(5, article.getRegdate());
 			pstmt.setInt(6, ref);
 			pstmt.setInt(7, step);
 			pstmt.setInt(8, depth);
 			pstmt.setString(9, article.getContent());
 			pstmt.setString(10, article.getIp());
 			pstmt.executeUpdate();
 		}catch(Exception ex) {
 			ex.printStackTrace();
 		}finally {
 			if(rs != null) try { rs.close(); }catch(SQLException ss) {}
 			if(pstmt != null) try { pstmt.close(); }catch(SQLException ss) {}
 			if(con != null) try { con.close(); }catch(SQLException ss) {}
 		}
 	}// end insertArticle	
	
	// 데이터 베이스에서 실제로 글 내용을 가져올 메소드 구현
 	
 // 데이터베이스에서 글 하나의 정보를 가져오는 메소드
 	public BoardVO getArticle(int num) {
 		
 		Connection con = null;
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		BoardVO article = null;
 		
 		try {
 			con=ConnUtil.getConnection();
 			
 			pstmt = con.prepareStatement(
 			"update board set readcount=readcount+1 where num=?");
 			pstmt.setInt(1, num);
 			pstmt.executeUpdate();
 			
 			pstmt =con.prepareStatement(
 				"select * from board where num =?");
 			pstmt.setInt(1, num);
 			rs = pstmt.executeQuery();
 			
 			if(rs.next()) {
 				article = new BoardVO();
 				article.setNum(rs.getInt("num"));
 				article.setWriter(rs.getString("writer"));
 				article.setEmail(rs.getString("email"));
 				article.setSubject(rs.getString("subject"));
 				article.setPass(rs.getString("pass"));
 				article.setRegdate(rs.getTimestamp("regdate"));
 				article.setReadcount(rs.getInt("readcount"));
 				article.setRef(rs.getInt("ref"));
 				article.setStep(rs.getInt("step"));
 				article.setDepth(rs.getInt("depth"));
 				article.setContent(rs.getString("content"));
 				article.setIp(rs.getString("ip"));
 			}
 		}catch(Exception ex) {
 			ex.printStackTrace();
 		}finally {
 			if(rs != null) try { rs.close(); }catch(SQLException ss) {}
 			if(pstmt != null) try { pstmt.close(); }catch(SQLException ss) {}
 			if(con != null) try { con.close(); }catch(SQLException ss) {}
 		}
 		return article;
 	}
	
 	// 수정 처리하려면 수정하려는 글의 데이터를 받아와야함
 	// 데이터를 받아올 메소드 구현
public BoardVO updateGetArticle(int num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO article = null;
		
		try {
			con = ConnUtil.getConnection();
			pstmt = con.prepareStatement(
					"select * from board where num=?");
			
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				article = new BoardVO();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setEmail(rs.getString("email"));
				article.setSubject(rs.getString("subject"));
				article.setPass(rs.getString("pass"));
				article.setRegdate(rs.getTimestamp("regdate"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setStep(rs.getInt("step"));
				article.setDepth(rs.getInt("depth"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(rs != null) try { rs.close(); }catch(SQLException ss) {}
			if(pstmt != null) try { pstmt.close(); }catch(SQLException ss) {}
			if(con != null) try { con.close(); }catch(SQLException ss) {}
		}
		return article;
	}


   // 글 수정처리를 실제로 데이터베이스에 적용시킬 메소드 구현

//글 수정을 처리해 줄 메소드 구현
	public int updateArticle(BoardVO article) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd ="";
		String sql="";
		int result = -1;
		
		try {
			
			con = ConnUtil.getConnection();
			pstmt = con.prepareStatement(
					"select pass from board where num=?");
			pstmt.setInt(1, article.getNum());
			rs = pstmt.executeQuery();
			
	if(rs.next()) {
		dbpasswd = rs.getString("pass");
		if(dbpasswd.equals(article.getPass())) {
	sql ="update board set writer=?, email=?, subject=?, content=? where num=?";
		pstmt = con.prepareStatement(sql);
	
		pstmt.setString(1, article.getWriter());
		pstmt.setString(2, article.getEmail());
		pstmt.setString(3, article.getSubject());
		pstmt.setString(4, article.getContent());
		pstmt.setInt(5, article.getNum());
		pstmt.executeUpdate();

		result = 1;// 수정 성공
		
		} else {
			result = 0; // 수정 실패
		}
	}
			
}catch(Exception ex) {
	ex.printStackTrace();
}finally {
	if(rs != null) try { rs.close(); }catch(SQLException ss) {}
	if(pstmt != null) try { pstmt.close(); }catch(SQLException ss) {}
	if(con != null) try { con.close(); }catch(SQLException ss) {}
}
		
		return result;
	}
	


	
	
	
	
	
	
	
	
}
