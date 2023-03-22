package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.exception.AuthenticateException;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.vo.MemberVO;

/**
 * 1. 둘 중 하나 혹은 파라미터가 누락되면, 400 에러 전송
 * 2. 인증 실패라면, loginForm으로 이동("로그인에 실패했음" 공유, attribute name : message)
 * 		-> loginForm에서 "로그인에 실패했음" 이라는 메세지를 swal로 출력. 
 * 3. 인증 성공 시, 웰컴 페이지로 이동 (인증에 성공한 사용자의 VO를 공유, attribute name : authMember).
 * 		-> "김은대"님 로그인 메세지 출력.
 */
@WebServlet("/login/loginProcess222")
public class LoginProcessControllerServlet2 extends HttpServlet{
		AuthenticateServiceImpl service = new AuthenticateServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memId = (String) req.getAttribute("memId");	
		String memPass = (String) req.getAttribute("memPass");	
		
		MemberVO vo = new MemberVO();
		vo.setMemId(memId);
		vo.setMemId(memPass);
		
		try {
			service.authenticate(vo);
		}catch (AuthenticateException e) {
			req.setAttribute("message", "로그인에 실패했음.");
		}
		if(memId==null||memPass==null) {
			resp.sendError(400);
		}else {
			service.authenticate(vo);
			req.setAttribute("authMember", vo.getMemId());
			req.getRequestDispatcher("/default.jsp").forward(req, resp);
			
		}
		
		
		
		
		
	}
	
}
