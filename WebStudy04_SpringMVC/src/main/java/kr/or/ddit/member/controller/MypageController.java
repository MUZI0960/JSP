package kr.or.ddit.member.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;

@Controller
public class MypageController{
	@Inject
	private MemberService service;
	
	@RequestMapping("/mypage.do")
	public String mypage(MemberVOWrapper principal, Model model) {
		MemberVO authMember = principal.getRealUser();
		
		MemberVO member = service.retrieveMember(authMember.getMemId());
		model.addAttribute("member", member);
		
		return "member/mypage";
	}
}

















