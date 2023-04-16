package kr.or.ddit.member.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.Pagination;
import kr.or.ddit.vo.SimpleCondition;

@Controller
public class MemberListController{
	@Inject
	private MemberService service;
	
	@RequestMapping("/member/memberList.do")
	public String memberList(
		@RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, @ModelAttribute("simpleCondition") SimpleCondition simpleCondition
		, Model model
	){
		
		Pagination<MemberVO> pagination = new Pagination<>(3, 2);
		pagination.setCurrentPage(currentPage);
		pagination.setSimpleCondition(simpleCondition);
		
		List<MemberVO> memberList = service.retrieveMemberList(pagination);
		
		pagination.setDataList(memberList);
		
		model.addAttribute("pagination", pagination);
		
//		pagination.setRenderer(new DefaultPaginationRenderer());
		
		return "member/memberList";
	}
}














