package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/logout")
public class LogoutControllerServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		// true -> 잘못된 요청 
		if(session.isNew()){
			resp.sendError(400, "유효 세션이 아님.");
			return;
		}
//		session.removeAttribute("authMember");
		session.invalidate(); // 유효하지 않는 세션으로 만들어줌.
		resp.sendRedirect(req.getContextPath() + "/");
	}
}
