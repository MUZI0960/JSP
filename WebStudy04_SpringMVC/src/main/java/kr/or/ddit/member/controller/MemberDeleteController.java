package kr.or.ddit.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/member/memberDelete.do")
public class MemberDeleteController{
	private final MemberService service;
	
	@PostMapping
	public String delete(
			MemberVOWrapper principal
			, HttpSession session
			, @RequestParam String password
	){
		
		MemberVO authMember = principal.getRealUser();
		MemberVO input = MemberVO.builder()
								.memId(authMember.getMemId())
								.memPass(password)
								.build();
		ServiceResult result = service.removeMember(input);
		String viewName = null;
		switch (result) {
		case INVALIDPASSWORD:
			session.setAttribute("message", "비번 오류");
			viewName = "redirect:/mypage.do";
			break;
		case OK:
			session.invalidate();
			viewName = "redirect:/";
			break;

		default:
			session.setAttribute("message", "서버 오류");
			viewName = "redirect:/mypage.do";
			break;
		}
		return viewName;
	}
}














