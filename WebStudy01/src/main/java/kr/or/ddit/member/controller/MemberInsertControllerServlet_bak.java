package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.internalResourceViewResolver;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberInsert222.do")
public class MemberInsertControllerServlet_bak extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	
	// 양식 제공 요청
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewName = "member/memberForm"; // logical view name
		new internalResourceViewResolver().viewResolve(viewName, req, resp);
	}
	
	// 데이터 처리 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String memId = req.getParameter("memId");
		String memPass = req.getParameter("memPass");
		String memName = req.getParameter("memName");
		String memRegno1 = req.getParameter("memRegno1");
		String memRegno2 = req.getParameter("memRegno2");
		String memBir = req.getParameter("memBir");
		String memZip = req.getParameter("memZip");
		String memAdd1 = req.getParameter("memAdd1");
		String memAdd2 = req.getParameter("memAdd2");
		String memHometel = req.getParameter("memHometel");
		String memComtel = req.getParameter("memComtel");
		String memHp = req.getParameter("memHp");
		String memMail = req.getParameter("memMail");
		String memJob = req.getParameter("memJob");
		String memLike = req.getParameter("memLike");
		String memMemorial = req.getParameter("memMemorial");
		String memMemorialday = req.getParameter("memMemorialday");
		int memMileage = Integer.parseInt(req.getParameter("memMileage"));
		String memDelete = req.getParameter("memDelete");
		
		MemberVO input = MemberVO.builder()
				.memId(memId)
				.memPass(memPass)
				.memName(memName)
				.memRegno1(memRegno1)
				.memRegno2(memRegno2)
				.memBir(memBir)
				.memZip(memZip)
				.memAdd1(memAdd1)
				.memAdd2(memAdd2)
				.memHometel(memHometel)
				.memComtel(memComtel)
				.memHp(memHp)
				.memMail(memMail)
				.memJob(memJob)
				.memLike(memLike)
				.memMemorial(memMemorial)
				.memMemorialday(memMemorialday)
				.memMileage(memMileage)
				.memDelete(memDelete)
				.build();
		
		ServiceResult result = service.createMember(input);
		
		String viewName = "redirect:/";
		new internalResourceViewResolver().viewResolve(viewName, req, resp);
		
	}
	
	// 수정하기
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
