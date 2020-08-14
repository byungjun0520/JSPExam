package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board.model.BoardVO;
import board.model.BoardDAO;

// 글 내용을 처리하는 액션 클래스
public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		// 해당 글 번호
		int num = Integer.parseInt(request.getParameter("num"));
		
		// 해당 페이지 번호
		String pageNum = request.getParameter("pageNum");
		
		// 데이터베이스 연결처리
		BoardDAO dbPro = BoardDAO.getInstance();
		// 해당 글 번호에 대한 해당레코드
		BoardVO  article = dbPro.getArticle(num);
		// 해당 뷰에서 사용할 속성
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		return "/board/content.jsp";
	}

}
