package kr.or.ddit.member.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;

/**
 * ex) /member/memberView.do?who=a001
 *
 */
@Controller
public class MemberViewController{
	@Inject
	private MemberService service;
	
	@RequestMapping("/member/memberView.do")
	public ModelAndView memView(@RequestParam("who") String memId){
		
		MemberVO member = service.retrieveMember(memId);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("member", member);
		mav.setViewName("member/memberView");

		return mav;
	}
}










