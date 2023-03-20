package kr.or.ddit.servlet04;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/11/sessionExtend")
public class SessionExtendServlet extends HttpServlet {
	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		System.out.printf("세션 아이디 : %s, 마지막 접속 시간 : %s\n", session.getId(), new Date(session.getLastAccessedTime()));
		
	}
}
