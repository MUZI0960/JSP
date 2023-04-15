package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.internalResourceViewResolver;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/mypage.do")
public class MypageControllerServlet extends HttpServlet{

	private MemberService service = new MemberServiceImpl();
	
	@Override
	   protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	      req.setCharacterEncoding("utf-8");
	      super.service(req, resp);
	   }
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVO authMember = (MemberVO)req.getSession().getAttribute("authMember");
		
		MemberVO member = service.retrieveMemer(authMember.getMemId());
		req.setAttribute("member", member);
		
		String viewName = "member/mypage";
		new internalResourceViewResolver().viewResolve(viewName, req, resp);
	}
	
	
	
	
	
	
	
	
}
