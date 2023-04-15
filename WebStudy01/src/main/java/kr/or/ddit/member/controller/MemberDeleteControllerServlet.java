package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.internalResourceViewResolver;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberDelete.do")
public class MemberDeleteControllerServlet extends HttpServlet{
	
	MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVO authMember =  (MemberVO) req.getSession().getAttribute("authMember");
		
		req.setCharacterEncoding("UTF-8");
		
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
		
		
		String viewName = "/";
		new internalResourceViewResolver().viewResolve(viewName, req, resp);
	}
	
}
