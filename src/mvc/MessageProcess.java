package mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// Controller로부터 작업의 처리를 지시 받아서 작업을 처리한다.
public class MessageProcess implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setAttribute("message", "요청 파라미터로 명령어를 전달");
		return "/mvc/process.jsp";
	}

}
