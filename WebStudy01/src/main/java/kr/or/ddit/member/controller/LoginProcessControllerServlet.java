package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.exception.AuthenticateException;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.mvc.internalResourceViewResolver;
import kr.or.ddit.vo.MemberVO;

/**
 * 1. 둘 중 하나 혹은 파라미터가 누락되면, 400 에러 전송
 * 2. 인증 실패라면, loginForm으로 이동("로그인에 실패했음" 공유, attribute name : message)
 * 		-> loginForm에서 "로그인에 실패했음" 이라는 메세지를 swal로 출력. 
 * 3. 인증 성공 시, 웰컴 페이지로 이동 (인증에 성공한 사용자의 VO를 공유, attribute name : authMember).
 * 		-> "김은대"님 로그인 메세지 출력.
 */
@WebServlet("/login/loginProcess")
public class LoginProcessControllerServlet extends HttpServlet{
	private	AuthenticateServiceImpl service = new AuthenticateServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session.isNew()) {
			resp.sendError(400);
			return;
		}
		
		req.setCharacterEncoding("UTF-8");
		String memId = req.getParameter("memId");
		String memPass = req.getParameter("memPass");
		
		MemberVO input = new MemberVO(); // command object - 검증 대상
		input.setMemId(memId);
		input.setMemPass(memPass);
		
		boolean valid = validate(input);
		String viewName = null;
		if(valid) {
			try {
				MemberVO saved = service.authenticate(input);
				session.setAttribute("authMember", saved);
				viewName = "/";
				
			}catch (AuthenticateException e) {
				String message = "로그인 실패";
				session.setAttribute("message", message);
				viewName = "/login/loginForm.jsp";
			}
			
		}else {
			session.setAttribute("message", "필수 파라미터 누락");
			viewName =  "/login/loginForm.jsp";
		}
//		resp.sendRedirect(req.getContextPath()+viewName);
		viewName = "redirect:"+viewName;
		new internalResourceViewResolver().viewResolve(viewName, req, resp);
	}

	private boolean validate(MemberVO input) {
		boolean valid = true;
		if(StringUtils.isBlank(input.getMemId())) {
			valid = false;
		}
		if(StringUtils.isBlank(input.getMemPass())) {
			valid = false;
		}
		return valid;
	}
	
}













